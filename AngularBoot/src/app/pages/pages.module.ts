import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { routing } from './pages.routing';
import { AuthGuard } from './_guards/index';
import { Pages } from './pages.component';
import { NgaModule } from '../theme/nga.module';
import { NearbyShopsComponent } from './nearbyShops/index';
import { PreferredShopsComponent } from './preferredShops/index';
/**
 * angular google maps
 */
import { AgmCoreModule } from '@agm/core';
/**
 * get environment variables for google maps
 */
import { environment } from '../../environments/environment';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    NgaModule,
    routing,
    AgmCoreModule.forRoot({
      apiKey: environment.googleMapsKey
    })
  ],
  declarations: [Pages,
    NearbyShopsComponent,
    PreferredShopsComponent],
  providers: [AuthGuard]
})
export class PagesModule {
}
