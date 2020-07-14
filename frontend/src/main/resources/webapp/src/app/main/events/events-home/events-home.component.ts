import { Component} from '@angular/core';
import { MatDialog } from '@angular/material';


import { EventsDetailComponent } from '../../../shared/events-detail/events-detail.component';
import { DomSanitizer } from '@angular/platform-browser';
import { IExpression, FilterExpressionUtils } from 'ontimize-web-ngx';
@Component({
  selector: 'app-events-home',
  templateUrl: './events-home.component.html',
  styleUrls: ['./events-home.component.scss']
})
export class EventsHomeComponent   {

  constructor(
    protected dialog: MatDialog,
    protected sanitizer: DomSanitizer
  ) { }




  public openDetail(data: any): void {
    this.dialog.open(EventsDetailComponent, {
      height: '330px',
      width: '520px',
      data: data
    });
  }

} 