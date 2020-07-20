import { Injectable, Injector } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CONFIG } from 'app/app.config';
import { OntimizeEEService, Observable } from 'ontimize-web-ngx';
import { share } from 'rxjs/operators';

@Injectable(
    {
        providedIn: 'root'
    }
)

export class BandService extends OntimizeEEService {

    buildHeaders() {
        const myData = JSON.parse(localStorage.getItem(CONFIG.uuid));
        return new HttpHeaders({
            'Access-Control-Allow-Origin': '*',
            'Content-Type': 'application/json;charset=UTF-8',
            'Authorization': 'Bearer ' + myData.session.id
        });
    }
    getBandDataId(id: number) {
        const url = CONFIG.apiEndpoint + '/' + 'bands/getBandSongs/search';
        var options = {
            headers: this.buildHeaders()
        };
        var body = JSON.stringify({
            filter: {
                band_id: id
            },
            columns: ["song_audio"],
            sqltypes: {
                "song_audio": 12
            }
        });
        console.log(body);
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

    getBandDataName(id: String) {
        const url = CONFIG.apiEndpoint + '/' + 'bands/getBandImage/search';
        var options = {
            headers: this.buildHeaders()
        };
        var body = JSON.stringify({
            filter: {
                band_name: id
            },
            columns: ["band_images_path"],
            sqltypes: {
                "band_images_path": 12
            }
        });
        console.log(body);
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