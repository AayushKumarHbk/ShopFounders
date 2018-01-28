import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ShopManagementService } from '../_services/index';
import { DaoShop, GetAllShopsStatus } from '../_models/index';

import 'style-loader!./landpage.scss';
@Component({
    selector: 'landpage',
    templateUrl: './landpage.html',
})
export class LandPageComponent implements OnInit {

    landpageHeading = '';
    private retrievedShops: DaoShop[] = new Array<DaoShop>();

    constructor(private router: Router,
        private shopService: ShopManagementService) { }

    ngOnInit() {
        this.getAllShops();
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
                    } else {
                        this.landpageHeading = getAllShopsStatus.getMessage();
                    }
                }
            }
        );
    }
}
