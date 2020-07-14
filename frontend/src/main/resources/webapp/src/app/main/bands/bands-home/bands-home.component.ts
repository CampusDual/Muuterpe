import { Component, OnInit } from '@angular/core';
import { BandsDetailComponent } from '../../../shared/bands-detail/bands-detail.component';
import { MatDialog } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-bands-home',
  templateUrl: './bands-home.component.html',
  styleUrls: ['./bands-home.component.scss']
})
export class BandsHomeComponent  {

  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer
  ) { }

  videos: string[] = [
    'SkypZuY6ZvA',
    '_N5YIGT_R58',
    'Ld0NDDYXj-c',
    'uelHwf8o7_U'
  ];

  public openDetail(data: any): void {
    this.dialog.open(BandsDetailComponent, {
      height: '98%',
      width: '80%',
      data: data
    });
  }

}
