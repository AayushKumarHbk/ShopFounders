import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { ShopManagementService } from '../_services/index';
import { DaoShop, GetAllShopsStatus, ShopLikeRequest, PreferredShopsRequest, RemoveLikeRequest } from '../_models/index';

import 'style-loader!./preferredShops.scss';

@Component({
    selector: 'preferred-shops',
    templateUrl: './preferredShops.html',
})
export class PreferredShopsComponent implements OnInit, OnDestroy {
    lat: number;
    lng: number;
    markers: any;
    subscription: any;
    noOfShops: number;

    pageHeading = '';
    private retrievedShops: DaoShop[] = new Array<DaoShop>();

    constructor(private router: Router,
        private shopService: ShopManagementService) { }

    ngOnInit() {
        this.getUserLocation();
        this.loadPreferredShops();
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

    private loadPreferredShops() {
        console.log('Calling ShopManagementService...');
        this.pageHeading = 'Fetching your preferred shops, Please wait...';
        const userId = this.getUserFromLocalStorage();
        if (userId != null) {
            const request = new PreferredShopsRequest();
            request.setUsername(userId);
            this.shopService.getPreferredShops(request).subscribe(
                data => {
                    console.log(JSON.stringify(data));
                    if (data.getPreferredShopsStatus() != null) {
                        const getPreferredShopsStatus = data.getPreferredShopsStatus();
                        if (getPreferredShopsStatus.getStatus()) {
                            this.retrievedShops = data.getShops();
                            this.noOfShops = this.retrievedShops.length;
                            this.pageHeading = 'Displaying ' + this.retrievedShops.length + ' shop(s) nearby';
                            // console.log(this.retrievedShops);
                            /* this.getDistance(); */
                        } else {
                            this.pageHeading = getPreferredShopsStatus.getMessage();
                        }
                    }
                }
            );
        } else {
            console.log('could not find user... terminating request');
        }
    }

    removeLike(_id) {
        console.log('NearbyShopsComponent::likeShop [ENTER]');
        const userId = this.getUserFromLocalStorage();
        if (userId != null) {
            const likeRequest = new RemoveLikeRequest(userId, _id);
            this.shopService.removeLike(likeRequest)
                .subscribe(result => {
                    if (result.getLikeStatus() != null) {
                        console.log(result.getLikeStatus());
                        if (result.getLikeStatus().getStatus()) {
                            document.getElementById(result.getShopId()).remove();
                            this.pageHeading = 'Displaying ' + --this.noOfShops + ' shop(s) nearby';
                        }
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
