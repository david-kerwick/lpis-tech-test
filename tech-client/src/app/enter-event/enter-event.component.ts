import { Component, OnInit } from '@angular/core';
import {EventClass} from '../event-class';
import {CalcService} from '../shared/payment/calc.service';
import {PaymentClass} from '../payment-class';

@Component({
  selector: 'app-enter-event',
  templateUrl: './enter-event.component.html',
  styleUrls: ['./enter-event.component.css']
})
export class EnterEventComponent implements OnInit {
  paymentList: Array<PaymentClass>;
  perilOptions = [
    {id: 1, name: "Hurricane"},
    {id: 2, name: "Earthquake"},
    {id: 3, name: "Flood"},
  ];

  regionOptions = [
    {id: 1, name: "California"},
    {id: 2, name: "Louisiana"},
    {id: 3, name: "Florida"},
  ];

  model = new EventClass(null, null, null, null);

  constructor(private calcService: CalcService) { }

  ngOnInit() {
  }

  submitted = false;

  onSubmit() { this.submitted = true;
  console.log('Submitted');
  console.log(this.model);
    this.calcService.getAll(this.model).subscribe(data => {
      console.log('Got data');
      console.log(data);
      this.paymentList = data;
    });
  }
}
