import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';
import { EventService } from '../../services/event.services'
import { ActivatedRoute, Params } from '@angular/router';
import { IEventModel } from '../../../shared/models/ievent.model';
import { parse } from 'querystring';
 
@Component({
  selector: 'app-events-detail',
  templateUrl: './events-detail.component.html',
  styleUrls: ['./events-detail.component.scss']
})
 
export class EventsDetailComponent implements OnInit {

  public parametro: any;
  public eventResult: IEventModel;
 
  constructor(
   @Inject(MAT_DIALOG_DATA) public data: any,
     protected sanitizer: DomSanitizer,
     private _route: ActivatedRoute,
        private eventService: EventService
  ) { }
 
 
 
  ngOnInit() {

      
      console.log('ngOnInit ID =>', Number.parseInt(this.data.event_id));

      this.ngOnStartEvent(Number.parseInt(this.data.event_id));
    
  }
  

  ngOnStartEvent(id: number) {

    console.log('ngOnStartEvent ID =>', id);
    this.eventService.getEventsData(id).subscribe(

        (eventData: any) => {

            if (eventData['data']) {

                if (eventData['data'].length > 0) {

                    this.eventResult = eventData['data'][0];

                } else {
                    this.eventResult = null;
                }
            }
        },
        err => console.error(err)
    );
}

getEventResult(){
  console.log('Contenido del evento', this.eventResult);
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