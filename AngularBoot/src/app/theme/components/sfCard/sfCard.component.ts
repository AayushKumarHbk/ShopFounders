import { Component, Input } from '@angular/core';

@Component({
  selector: 'sf-card',
  templateUrl: './sfCard.html',
})

export class SFCard {
  @Input() title: String;
  @Input() baCardClass: String;
  @Input() cardType: String;

  constructor() {
  }

}
