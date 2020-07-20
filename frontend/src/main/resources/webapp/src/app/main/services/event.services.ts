import { Injectable, Injector } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { CONFIG } from "app/app.config";
import { OntimizeEEService, Observable } from "ontimize-web-ngx";
import { share } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class EventService extends OntimizeEEService {




    buildHeaders() {
        const myData = JSON.parse(localStorage.getItem(CONFIG.uuid));
        return new HttpHeaders({
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': 'Bearer ' + myData.session.id
        });
    }
    getEventsData(id: number) {
        const url = CONFIG.apiEndpoint + '/' + 'events/getEventsById/search';
        var options = {
            headers: this.buildHeaders()
        };
        var body = JSON.stringify({
            filter: {
                event_id: id
            },
            columns: ["band_name", "event_region", "event_name", "event_date_time"],
            sqltypes: {

                "band_name": 12,
                "event_region": 12,
                "event_name": 12,
                "event_date_time": 91
            }
        });
        var self = this;
        var dataObservable = new Observable(function (_innerObserver) {
            self.httpClient.post(url, body, options).subscribe(function (resp) {
                self.parseSuccessfulQueryResponse(resp, _innerObserver);
            }, function (error) {
                self.parseUnsuccessfulQueryResponse(error, _innerObserver);
            }, function () { return _innerObserver.complete(); });
        });
        return dataObservable.pipe(share());
    }

}

