import {
  Component,
  OnInit,
  Inject,
  ViewChild,
  SystemJsNgModuleLoader,
} from "@angular/core";
import { DomSanitizer } from "@angular/platform-browser";
import { MAT_DIALOG_DATA } from "@angular/material";

import { IImage } from "ng-simple-slideshow";

import { ActivatedRoute } from "@angular/router";
import { BandService } from "app/main/services/band.services";
import { IBandModel } from "../models/iband.model";
import { forEach } from "@angular/router/src/utils/collection";
import { FormGroup, FormControl, Validators, ValidationErrors } from "@angular/forms";
import { IBandCommentModel } from "../models/ibandcomment.model";
import { nextTick } from "process";

@Component({
  selector: "app-bands-detail",
  templateUrl: "./bands-detail.component.html",
  styleUrls: ["./bands-detail.component.scss"],
})
export class BandsDetailComponent implements OnInit {
  public parametro: any;
  public bandResultName: IBandModel;
  public bandResult: IBandModel;
  public bandCommentResult: IBandCommentModel;
  public bandCommentInsertResult: IBandCommentModel = null;
  public registerForm: FormGroup = null;
  public alias: String;

  private bandComment: IBandCommentModel = {
    band_id: this.data.band_id,
    comment_alias: "",
    comment_body: "",
  };
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private bandService: BandService
  ) {}

  public arrayImages: (string | IImage)[] = [
    "assets/images/bands/slider/BEEGEES_1.jpg",
    "assets/images/bands/slider/BEEGEES_2.jpg",
    "assets/images/bands/slider/BEEGEES_3.jpg",
    "assets/images/bands/slider/BEEGEES_4.jpg",
  ];
  showArrows: boolean = true;
  showDots: boolean = true;
  autoPlay: boolean = true;

  ngOnInit() {
    this.alias = this.data.band_name.replace(/\s/g, "");
    this.ngOnStartBandId(this.data.band_id);
    this.ngOnStartBandName(this.alias);
    this.ngOnStartComments(this.data.band_id);
    this.registerForm = this.createForm();
    this.bandComment.band_id = this.data.band_id;
  }

  ngOnStartBandId(id: number) {
    this.bandService.getBandDataId(id).subscribe(
      (bandData: any) => {
        if (bandData["data"]) {
          if (bandData["data"].length > 0) {
            this.bandResult = bandData["data"];
          } else {
            this.bandResult = null;
          }
        }
      },
      (err) => console.error(err)
    );
  }

  ngOnStartComments(id: number) {
    this.bandService.getCommentsData(id).subscribe(
      (bandCommentData: any) => {
        if (bandCommentData["data"]) {
          if (bandCommentData["data"].length > 0) {
            this.bandCommentResult = bandCommentData["data"];
          } else {
            this.bandCommentResult = null;
          }
        }
      },
      (err) => console.error(err)
    );
  }

  getBandResult() {
    return this.bandResult;
  }

  getCommentResult() {
    console.log(this.bandCommentResult);
    return this.bandCommentResult;
  }

  ngOnStartBandName(id: String) {
    this.bandService.getBandDataName(id).subscribe(
      (bandDataName: any) => {
        if (bandDataName["data"]) {
          if (bandDataName["data"].length > 0) {
            this.bandResultName = bandDataName["data"];
          } else {
            this.bandResultName = null;
          }
        }
      },
      (err) => console.error(err)
    );
  }

  getBandResultName() {
    console.log(this.bandResultName);

    return this.bandResultName;
  }
  createForm() {
    return new FormGroup({
      nick: new FormControl("", [
        Validators.minLength(3),
        Validators.maxLength(25),
       
      ]),
      
      comment_body: new FormControl("", [
        Validators.minLength(3),
        Validators.maxLength(200),
      ]),
    });
    
  }
  nickValidator(control:FormControl):ValidationErrors{
    if (control.value == "" ||control.value <3) {
      return {
        'invalidNick': true
      };
    }
    return {};
  }
  
 
  createComment() {

    if(this.registerForm.valid){
      this.bandComment.comment_body = this.registerForm.value.comment_body;
      this.bandComment.comment_alias = this.registerForm.value.nick;
   
      this.bandService.commentInsert(this.bandComment).subscribe(
        (bandCommentData: any) => {
          if (bandCommentData["data"]) {
            if (bandCommentData["data"].length > 0) {
              this.bandCommentInsertResult = bandCommentData["data"][0];
              this.registerForm = this.createForm();
              return this.bandCommentInsertResult;
            } else {
              this.bandCommentInsertResult = null;
            }
          }
          this.ngOnStartComments(this.data.band_id);
          this.onResetForm();
        },
        (err) => {
         alert("Oooops, something went wrong");
        }
      );

    }

  
  }

  onResetForm(): void {
    this.registerForm.reset();
  }
  getErrorMessage() {
    if (this.registerForm.value.nick.hasError('required')) {
      return 'You must enter a value';
    }
    return this.registerForm.value.nick.hasError('minLength') ? 'Not a valid nickname' : '';
  }
}
