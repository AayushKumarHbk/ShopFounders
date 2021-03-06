import { Directive, ElementRef, HostListener, HostBinding } from '@angular/core';

import { SFCardBlurHelper } from './sfCardBlurHelper.service';
import { SFMetrics } from './sfMetrics';

@Directive({
  selector: '[sfCardBlur]',
  providers: [SFCardBlurHelper]
})
export class SFCardBlur {

  private _bodyBgSize: SFMetrics;

  constructor(private _baCardBlurHelper: SFCardBlurHelper, private _el: ElementRef) {
    this._baCardBlurHelper.init();
    this._getBodyImageSizesOnBgLoad();
    this._recalculateCardStylesOnBgLoad();

  }

  @HostListener('window:resize')
  _onWindowResize(): void {
    this._bodyBgSize = this._baCardBlurHelper.getBodyBgImageSizes();
    this._recalculateCardStyle();
  }

  private _getBodyImageSizesOnBgLoad(): void {
    this._baCardBlurHelper.bodyBgLoad().subscribe(() => {
      this._bodyBgSize = this._baCardBlurHelper.getBodyBgImageSizes();
    });
  }

  private _recalculateCardStylesOnBgLoad(): void {
    this._baCardBlurHelper.bodyBgLoad().subscribe((event) => {
      setTimeout(this._recalculateCardStyle.bind(this));
    });
  }

  private _recalculateCardStyle(): void {
    if (!this._bodyBgSize) {
      return;
    }
    this._el.nativeElement.style.backgroundSize = Math.round(this._bodyBgSize.width) + 'px ' + Math.round(this._bodyBgSize.height) + 'px';
    this._el.nativeElement.style.backgroundPosition = Math.floor(this._bodyBgSize.positionX) + 'px '
      + Math.floor(this._bodyBgSize.positionY) + 'px';
  }
}
