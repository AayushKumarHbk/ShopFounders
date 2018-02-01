import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, ActivatedRoute } from '@angular/router';
import { ShopManagementService } from '../_services/index';
import { DaoShop, GetAllShopsStatus, ShopLikeRequest, PreferredShopsRequest } from '../_models/index';

import 'style-loader!./nearbyShops.scss';

@Component({
    selector: 'nearby-shops',
    templateUrl: './nearbyShops.html',
})
export class NearbyShopsComponent implements OnInit, OnDestroy {
    lat: number;
    lng: number;
    markers: any;
    noOfShops: number;
    private locationErrorMessage: string;
    mapLoaded: boolean;

    pageHeading = '';
    private retrievedShops: DaoShop[] = new Array<DaoShop>();

    constructor(private router: Router,
        private shopService: ShopManagementService) { }

    ngOnInit() {
        this.getUserLocation();
    }

    ngOnDestroy() {
    }

    private getUserLocation() {
        this.pageHeading = 'Getting user location...';
        /// locate the user
        const self = this;
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(
                function (position) {
                    self.lat = position.coords.latitude;
                    self.lng = position.coords.longitude;
                    console.log('your location', position);
                    self.mapLoaded = true;
                    self.getNearbyShops();
                }, function () {
                    self.locationErrorMessage = 'Could not retrieve user location...';
                    self.mapLoaded = false;
                    self.getNearbyShops();
                });
        }
    }

    getNearbyShops() {
        console.log('Calling ShopManagementService...');
        this.pageHeading = 'Fetching shops, Please wait...';
        const userId = this.getUserFromLocalStorage();
        if (userId != null) {
            const request = new PreferredShopsRequest();
            request.setUsername(userId);
            this.shopService.getNearbyShops(request).subscribe(
                data => {
                    if (data.getPreferredShopsStatus() != null) {
                        const getNearbyShopsStatus = data.getPreferredShopsStatus();
                        if (getNearbyShopsStatus.getStatus()) {
                            const shopsList = data.getShops();
                            if (this.mapLoaded) {
                                for (const shop of shopsList) {
                                    console.log(shop.getLocation());
                                    const coords = shop.getLocation().getCoordinates();
                                    shop.setDistance(this.distance(coords[0], coords[1], this.lat, this.lng, 'K'));
                                }
                                shopsList.sort((a, b) => {
                                    return a.getDistance() - b.getDistance();
                                });
                            } else {
                                this.locationErrorMessage = this.locationErrorMessage
                                    + ' Shop List will not be sorted';
                            }
                            this.retrievedShops = shopsList;
                            this.noOfShops = this.retrievedShops.length;
                            this.pageHeading = 'Displaying ' + this.retrievedShops.length + ' shops nearby';
                            // console.log(this.retrievedShops);
                            /* this.getDistance(); */
                        } else {
                            this.pageHeading = getNearbyShopsStatus.getMessage();
                        }
                    }
                }
            );
        } else {
            console.log('could not find user... terminating request');
        }
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

    public distance(lat1, lon1, lat2, lon2, unit) {
        console.log(lat1, lon1, lat2, lon2, unit);
        const radlat1 = Math.PI * lat1 / 180;
        const radlat2 = Math.PI * lat2 / 180;
        const radtheta = Math.PI * (lon1 - lon2) / 180;
        let dist = Math.sin(radlat1) * Math.sin(radlat2) + Math.cos(radlat1) * Math.cos(radlat2) * Math.cos(radtheta);
        dist = Math.acos(dist);
        dist = dist * 180 / Math.PI;
        dist = dist * 60 * 1.1515;
        if (unit === 'K') { dist = dist * 1.609344; }
        if (unit === 'N') { dist = dist * 0.8684; }
        console.log(dist);
        return Math.round(dist * 100) / 100;
    }
}
