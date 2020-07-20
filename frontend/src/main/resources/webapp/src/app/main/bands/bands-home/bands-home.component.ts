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

  public openDetail(data: any): void {
    this.dialog.open(BandsDetailComponent, {
      height: '98%',
      width: '70%',
      data: data
    });
  }

}
