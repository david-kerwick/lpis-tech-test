import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {EventClass} from '../../event-class';

@Injectable({
  providedIn: 'root'
})
export class CalcService {

  constructor(private http: HttpClient) { }

  getAll(event: EventClass): Observable<any> {
    let eventList: Array<EventClass> = [];
    eventList.push(event);

    return this.http.post('//localhost:8080/', eventList);
  }
}
