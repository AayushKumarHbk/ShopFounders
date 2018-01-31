import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { ShopManagementService } from '../_services/index';
import { DaoShop, GetAllShopsStatus, ShopLikeRequest } from '../_models/index';

import 'style-loader!./nearbyShops.scss';

@Component({
    selector: 'nearby-shops',
    templateUrl: './nearbyShops.html',
})
export class NearbyShopsComponent implements OnInit, OnDestroy {
    lat: number;
    lng: number;
    markers: any;
    subscription: any;

    pageHeading = '';
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
        this.pageHeading = 'Fetching shops, Please wait...';
        this.shopService.getAllShops().subscribe(
            data => {
                if (data.getGetAllShopsStatus() != null) {
                    const getAllShopsStatus = data.getGetAllShopsStatus();
                    if (getAllShopsStatus.getStatus()) {
                        this.retrievedShops = data.getShops();
                        this.pageHeading = 'Displaying ' + this.retrievedShops.length + ' shops nearby';
                        // console.log(this.retrievedShops);
                        /* this.getDistance(); */
                    } else {
                        this.pageHeading = getAllShopsStatus.getMessage();
                    }
                }
            }
        );
    }

    likeShop(_id, likeType) {
        console.log('NearbyShopsComponent::likeShop [ENTER]');
        const userId = this.getUserFromLocalStorage();
        if (userId != null) {
            console.log('like type: ' + likeType);
            const likeRequest = new ShopLikeRequest(userId, _id, likeType);
            console.log(JSON.stringify(likeRequest));
            this.shopService.processLike(likeRequest)
                .subscribe(result => {
                    if (result.getLikeStatus() != null) {
                        console.log(result.getLikeStatus());
                    } else {
                        console.log('request failed');
                    }
                });
        } else {
            console.log('could not find user... terminating request');
        }
    }

    getUserFromLocalStorage(): string {
        console.log('fetching username from localStorage');
        const currentUser = localStorage.getItem('currentUser');
        if (currentUser != null || currentUser !== '') {
            const userArray = JSON.parse(currentUser)[0];
            return userArray.username;
        }
        return null;
    }
}
