import {Component, OnInit} from '@angular/core';
import {DealListService} from '../shared/payment/deal-list.service';
import {DealClass} from '../deal-class';

@Component({
  selector: 'app-deal-list',
  templateUrl: './deal-list.component.html',
  styleUrls: ['./deal-list.component.css']
})
export class DealListComponent implements OnInit {
  dealList: Array<DealClass>;

  constructor(private dealService: DealListService) {
  }

  ngOnInit() {
    this.dealService.getAll().subscribe(data => {
      this.dealList = data;
    });
  }

  getRegionsString(regions: Array<number>): Array<string> {
    let stringArray: Array<string> = [];
    for (let region of regions) {
      if (region == 1) {
        stringArray.push('California');
      } else if (region == 2) {
        stringArray.push('Louisiana');
      } else if (region == 3) {
        stringArray.push('Florida');
      }
    }
    return stringArray;
  }

  getPerilsString(perils: Array<number>): Array<string> {
    let stringArray: Array<string> = [];
    for (let peril of perils) {
      if (peril == 1) {
        stringArray.push('Hurricane');
      } else if (peril == 2) {
        stringArray.push('Earthquake');
      } else if (peril == 3) {
        stringArray.push('Flood');
      }
    }
    return stringArray;
  }

}
