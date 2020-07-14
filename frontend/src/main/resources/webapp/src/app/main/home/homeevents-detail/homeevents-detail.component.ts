import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-homeevents-detail',
  templateUrl: './homeevents-detail.component.html',
  styleUrls: ['./homeevents-detail.component.scss']
})
export class HomeeventsDetailComponent implements OnInit {

  constructor(
    @Inject(MAT_DIALOG_DATA) public data: any,
      protected sanitizer: DomSanitizer
   ) { }
  
  
  
   ngOnInit() {
   }
   
   public openURL(data): void {
    
     try {
       /* event service de eventos necesita cambios en nextEventsQuery() */
       if(data.event_buy_tickets!= null && data.event_buy_tickets!= ""){
         window.open(data.event_buy_tickets);
       }
     } catch (error) {
       confirm("{{'ERROR' | oTranslate }}");
     }
     
     
   
   }
}
