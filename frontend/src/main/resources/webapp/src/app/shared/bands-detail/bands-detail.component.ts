import { Component, OnInit, Inject, ViewChild, SystemJsNgModuleLoader } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MAT_DIALOG_DATA } from '@angular/material';
import { IImage } from 'ng-simple-slideshow';

/* declare var fs: any; */

@Component({
  selector: 'app-bands-detail',
  templateUrl: './bands-detail.component.html',
  styleUrls: ['./bands-detail.component.scss']
})

export class BandsDetailComponent implements OnInit {
   
  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
    protected sanitizer: DomSanitizer

  ) { }

  alias:string;
  /*  fs = require('fs');
   files : String = this.fs.readdirSync('/assets/images/bands/slider/'); */

  public arrayImages:(string | IImage ) [] = [
   /*  { url: 'assets/images/bands/BEEGEES1.jpg'},
    { url: 'assets/images/bands/BEEGEES2.jpg'},
    { url: 'assets/images/bands/BEE GEES3.jpg'},
    { url: 'assets/images/bands/AMY WINEHOUSE.jpg' } */
  ];
  showArrows:boolean = true;
  showDots:boolean = true;
  autoPlay:boolean = true;


  videos: string[] = [
    'SkypZuY6ZvA',
    '_N5YIGT_R58',
    'Ld0NDDYXj-c',
    'uelHwf8o7_U'
  ];

  ngOnInit() {
  
    this.alias = this.data.band_name.replace(/\s/g,"");
    console.log('bandname ' +this.alias);
   
   /*  for(let i = 0; i < this.files.length; i++){
      console.log('hola'  +this.files[i]);
      if(this.files[i].match('assets/images/bands/slider/'+this.alias)){
        this.arrayImages.push(this.files[i]);
      }
    } */
    
     for( let i = 1; i < 6; i++){
     if('assets/images/bands/slider/' + this.alias + i +'.jpg'.includes(this.alias+i)){
          this.arrayImages.push('assets/images/bands/slider/' + this.alias + i + '.jpg');
        } else{break;}
       
    } 
    
    setTimeout(() => {
      this.arrayImages;
    }, 2000);
  }
  
}



