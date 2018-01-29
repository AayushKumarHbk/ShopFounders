import { NgModule, ModuleWithProviders } from '@angular/core';
import { NavBarComponent, SFCard } from './components/index';
import { RouterModule } from '@angular/router';

import { SFCardBlur } from './components/sfCard/sfCardBlur.directive';
import { CommonModule } from '@angular/common';

@NgModule({
  declarations: [
    NavBarComponent,
    SFCardBlur,
    SFCard,
  ],
  imports: [RouterModule,
    CommonModule],
  exports: [
    NavBarComponent,
    SFCardBlur,
    SFCard,
  ],
  providers: [],
  schemas: []
})

export class NgaModule {
  static forRoot(): ModuleWithProviders {
    return <ModuleWithProviders>{
      ngModule: NgaModule,
      providers: [
      ],
    };
  }
}
