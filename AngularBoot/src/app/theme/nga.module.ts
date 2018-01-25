import { NgModule, ModuleWithProviders } from '@angular/core';
import { NavBarComponent } from './components/index';
import { RouterModule } from '@angular/router';

@NgModule({
  declarations: [NavBarComponent],
  imports: [RouterModule],
  exports: [NavBarComponent],
  providers: [],
  schemas: []
})

export class NgaModule {
  static forRoot(): ModuleWithProviders {
    return <ModuleWithProviders> {
      ngModule: NgaModule,
      providers: [
      ],
    };
  }
}
