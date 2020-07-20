import { Component, OnInit, Inject, ViewChild, SystemJsNgModuleLoader } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MAT_DIALOG_DATA } from '@angular/material';
import { IImage } from 'ng-simple-slideshow';
import { ActivatedRoute } from '@angular/router';
import { BandService } from 'app/main/services/band.services';
import { IBandModel } from '../models/iband.model';


@Component({
  selector: 'app-bands-detail',
  templateUrl: './bands-detail.component.html',
  styleUrls: ['./bands-detail.component.scss']
})

export class BandsDetailComponent implements OnInit {

  public parametro: any;
  public bandResult: IBandModel;
  public alias: String;

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private bandService: BandService

  ) { }

  panelOpenState = false;

  public arrayImages: (string | IImage)[] = [];
  public arrayVideos: (string)[] = [];


  showArrows: boolean = true;
  showDots: boolean = true;
  autoPlay: boolean = true;

  ngOnInit() {
    this.alias = this.data.band_name.replace(/\s/g, "");
    this.ngOnStartBandId(this.data.band_id);
    this.ngOnStartBandName(this.alias);
  }

  ngOnStartBandId(id: number) {
    this.bandService.getBandDataId(id).subscribe(
      (bandData: any) => {
        if (bandData['data']) {
          bandData['data'].forEach((value: IBandModel, key: string) => {
            this.arrayVideos.push(value.song_audio);
          });
        }
      },
      err => console.error(err)
    );
  }

  ngOnStartBandName(id: String) {
    this.bandService.getBandDataName(id).subscribe(
      (bandDataName: any) => {
        if (bandDataName['data']) {
          bandDataName['data'].forEach((value: IBandModel, key: string) => {
            this.arrayImages.push(value.band_images_path);
          });
        }
      },
      err => console.error(err)
    );
  }
}



