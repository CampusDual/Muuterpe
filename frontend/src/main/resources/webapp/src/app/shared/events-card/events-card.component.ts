import { Component, OnInit, ChangeDetectorRef, ViewEncapsulation } from '@angular/core';
import { OntimizeService } from 'ontimize-web-ngx';
 
declare var d3: any;
@Component({
  selector: 'app-events-card',
  templateUrl: './events-card.component.html',
  styleUrls: ['./events-card.component.scss'],
  encapsulation: ViewEncapsulation.None,
  host: {
    '[class.events-card]': 'true'
  }
})
export class EventsCardComponent {
 
 
  public eventssAmount: number;
 
  constructor(
    private ontimizeService: OntimizeService,
    private cd: ChangeDetectorRef
  ) {
    this.ontimizeService.configureService(this.ontimizeService.getDefaultServiceConfiguration('events'));
    this.ontimizeService.query(void 0, ['event_id'], 'event').subscribe(
      res => this.eventssAmount = (res.data && res.data.length) ? res.data.length : void 0,
      err => console.log(err),
      () => this.cd.detectChanges()
    );
 
    
  
  }
 
}