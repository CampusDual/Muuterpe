import { Component, OnInit, Inject, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MAT_DIALOG_DATA } from '@angular/material';
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
  public bandResultName: IBandModel;
  public bandResult: IBandModel;

  public alias: String;


  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer,
    private _route: ActivatedRoute,
    private bandService: BandService

  ) { }



  ngOnInit() {
    this.alias = this.data.band_name.replace(/\s/g,"");
    this.ngOnStartBandId(this.data.band_id);
    this.ngOnStartBandName(this.alias);
    }
    
    ngOnStartBandId(id: number) {
      this.bandService.getBandDataId(id).subscribe(
          (bandData: any) => {
              if (bandData['data']) {
                  if (bandData['data'].length > 0) {
                    this.bandResult = bandData['data'];
                  } else {
                      this.bandResult = null;
                  }
              }
          },
          err => console.error(err)
      );
  }
  
  getBandResult(){
    return this.bandResult;
  }

  ngOnStartBandName(id: String) {
    this.bandService.getBandDataName(id).subscribe(
        (bandDataName: any) => {
            if (bandDataName['data']) {
                if (bandDataName['data'].length > 0) {
                  this.bandResultName = bandDataName['data'];
                } else {
                    this.bandResultName = null;
                }
            }
        },
        err => console.error(err)
    );
}

getBandResultName(){
  console.log(this.bandResultName);
  return this.bandResultName;
}


}



