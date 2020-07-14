import { Component, OnInit, Inject, ViewChild} from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { MAT_DIALOG_DATA } from '@angular/material';



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
  
 
  videos: string[] = [
    'SkypZuY6ZvA',
    '_N5YIGT_R58',
    'Ld0NDDYXj-c',
    'uelHwf8o7_U'
  ];


  ngOnInit()  {}


}
