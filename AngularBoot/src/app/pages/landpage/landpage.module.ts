import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { routing } from './landpage.routing';
import { LandPageComponent } from './landpage.component';
import { NgaModule } from '../../theme/nga.module';
import { AgmCoreModule } from '@agm/core';
import { environment } from '../../../environments/environment';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    routing,
    NgaModule,
    AgmCoreModule.forRoot({
      apiKey: environment.googleMapsKey
    })
  ],
  declarations: [
    LandPageComponent
  ]
})
export class LandpageModule {
}
