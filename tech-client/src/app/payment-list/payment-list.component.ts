import {Component, Input, OnInit} from '@angular/core';
import {PaymentClass} from '../payment-class';

@Component({
  selector: 'app-payment-list',
  templateUrl: './payment-list.component.html',
  styleUrls: ['./payment-list.component.css']
})
export class PaymentListComponent implements OnInit {
  @Input() paymentResults: Array<PaymentClass>;

  constructor() {
  }

  ngOnInit() {

  }

}
