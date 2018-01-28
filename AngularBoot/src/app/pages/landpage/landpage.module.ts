import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { routing } from './landpage.routing';
import { LandPageComponent } from './landpage.component';
import { NgaModule } from '../../theme/nga.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    routing,
    NgaModule
  ],
  declarations: [
    LandPageComponent
  ]
})
export class LandpageModule {
}
