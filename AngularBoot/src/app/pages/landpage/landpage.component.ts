import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { ShopManagementService } from '../_services/index';
import { DaoShop, GetAllShopsStatus } from '../_models/index';

import 'style-loader!./landpage.scss';

@Component({
    selector: 'landpage',
    templateUrl: './landpage.html',
})
export class LandPageComponent implements OnInit, OnDestroy {
    lat: number;
    lng: number;
    markers: any;
    subscription: any;

    landpageHeading = '';
    private retrievedShops: DaoShop[] = new Array<DaoShop>();

    constructor(private router: Router,
        private shopService: ShopManagementService) { }

    ngOnInit() {
        this.getUserLocation();
        this.getAllShops();
    }

    ngOnDestroy() {
    }

    private getUserLocation() {
     /// locate the user
     if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(position => {
         this.lat = position.coords.latitude;
         this.lng = position.coords.longitude;
         console.log('your location', position);
       });
     }
   }

    getAllShops() {
        console.log('Calling ShopManagementService...');
        this.landpageHeading = 'Fetching shops, Please wait...';
        this.shopService.getAllShops().subscribe(
            data => {
                console.log(JSON.stringify(data));
                if (data.getGetAllShopsStatus() != null) {
                    const getAllShopsStatus = data.getGetAllShopsStatus();
                    if (getAllShopsStatus.getStatus()) {
                        this.retrievedShops = data.getShops();
                        this.landpageHeading = 'Displaying ' + this.retrievedShops.length + ' shops';
                        console.log(this.retrievedShops);
                        /* this.getDistance(); */
                    } else {
                        this.landpageHeading = getAllShopsStatus.getMessage();
                    }
                }
            }
        );
    }
}
