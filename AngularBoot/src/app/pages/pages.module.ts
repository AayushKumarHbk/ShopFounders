import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { routing } from './pages.routing';
import { AuthGuard } from './_guards/index';
import { Pages } from './pages.component';
import { NgaModule } from '../theme/nga.module';

@NgModule({
  imports: [CommonModule, NgaModule, routing],
  declarations: [Pages],
  providers: [AuthGuard]
})
export class PagesModule {
}
