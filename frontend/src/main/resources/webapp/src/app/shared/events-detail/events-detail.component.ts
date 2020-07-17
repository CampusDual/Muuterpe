import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { EventService } from '../../main/services/event.services'
import { ActivatedRoute, Params } from '@angular/router';
import { IEventModel } from '../models/ievent.model';
 
@Component({
  selector: 'app-events-detail',
  templateUrl: './events-detail.component.html',
  styleUrls: ['./events-detail.component.scss']
})
 
export class EventsDetailComponent implements OnInit {

  public parametro: any;
  public eventResult: IEventModel;
  public arrayEvent :any[];
 
  constructor(
   @Inject(MAT_DIALOG_DATA) public data: any,
     protected sanitizer: DomSanitizer,
     private _route: ActivatedRoute,
    private eventService: EventService
  ) { }
 
 

  ngOnInit() {
  this.ngOnStartEvent(this.data.event_id);
  }
  
  ngOnStartEvent(id: number) {
    this.eventService.getEventsData(id).subscribe(
        (eventData: any) => {
            if (eventData['data']) {
                if (eventData['data'].length > 0) {
                  this.eventResult = eventData['data'];
                } else {
                    this.eventResult = null;
                }
            }
        },
        err => console.error(err)
    );
}

getEventResult(){
  this.arrayEvent = Array.of(this.eventResult); 
  return this.eventResult;
}

  public openURL(data): void {
    try {
      if(data.event_buy_tickets!= null && data.event_buy_tickets!= ""){
        window.open(data.event_buy_tickets);
      }
    } catch (error) {
      confirm("{{'ERROR' | oTranslate }}");
    }

  }


}