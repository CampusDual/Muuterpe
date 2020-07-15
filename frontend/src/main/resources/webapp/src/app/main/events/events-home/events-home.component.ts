import { Component} from '@angular/core';
import { MatDialog } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { IExpression, FilterExpressionUtils } from 'ontimize-web-ngx';
import { EventsDetailComponent } from 'app/shared/events-detail/events-detail.component';
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