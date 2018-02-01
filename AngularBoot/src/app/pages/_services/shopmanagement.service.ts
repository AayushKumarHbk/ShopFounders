import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import 'rxjs/add/operator/map';
import { Router } from '@angular/router';

import {
    DaoShop,
    DaoShopLocation,
    ShopLikeRequest,
    ShopLikeStatus,
    ShopLikeResponse,
    PreferredShopsRequest,
    RemoveLikeRequest,
    RemoveLikeStatus,
    RemoveLikeResponse,
    PreferredShopsResponse,
    PreferredShopsStatus
} from '../_models/index';
import { WebserviceErrorHandler } from '../errohandler/errorhandler.component';
import { PreferredShopsComponent } from '../preferredShops/index';


@Injectable()
export class ShopManagementService {

    private _url_shop_getAllShops = 'http://localhost:8080/shop/getAllShops';
    private _url_shop_nearbyShops = 'http://localhost:8080/shop/nearbyShops';
    private _url_Shop_ProcessLikes = 'http://localhost:8080/shop/processLikes';
    private _url_shop_preferredShops = 'http://localhost:8080/shop/preferredShops';
    private _url_shop_removeLike = 'http://localhost:8080/shop/removeLike';
    http: Http;
    public errorhandler: WebserviceErrorHandler;

    constructor(private router: Router,
        http: Http) {
        this.http = http;
        // set token if saved in local storage
        const currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.errorhandler = new WebserviceErrorHandler();
    }

    public getOptions(): RequestOptions {
        // creates Http Header
        const headers: Headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Accept', 'application/json');
        return new RequestOptions({ headers });
    }

    /**
     * Validates the request, creates an HTTP Request package
     * and makes a post request to the REST service
     */
    public processLike(request: ShopLikeRequest): Observable<ShopLikeResponse> {
        // check if request is null
        if (request != null && request.getUserId() && request.getShopId() && request.getLikeType() !== undefined) {

            console.log('ProcessLike Request: ' + request);
            console.log('Connecting to Shop Service...');

            // making a post request to Shop Service
            return this.http.post(this._url_Shop_ProcessLikes, request, this.getOptions())
                .map(data => this.extractProcessLikeShopData(data))
                .catch(this.errorhandler.handleError);
        }
    }

    /**
     * Validates the request, creates an HTTP Request package
     * and makes a post request to the REST service
     */
    public removeLike(request: RemoveLikeRequest): Observable<ShopLikeResponse> {
        // checks for empty request
        if (request != null && request.getUserId() && request.getShopId()) {

            console.log('removeLike Request: ' + request);
            console.log('Connecting to Shop Service...');

            // making a post request to Shop Service
            return this.http.post(this._url_shop_removeLike, request, this.getOptions())
                .map(data => this.extractProcessLikeShopData(data))
                .catch(this.errorhandler.handleError);
        }
    }

    /**
     * Parses the received response
     * and returns a response package
     */
    private extractRemoveLikeShopData(res: Response): RemoveLikeResponse {
        const responsePackage: RemoveLikeResponse = new RemoveLikeResponse();
        const statusPackage: RemoveLikeStatus = new RemoveLikeStatus();
        console.log('Shop Connection Attempt successful\nRemoveLikeResponse obtained from Shop Service');
        if (res.status === 200 && res.statusText === 'OK') {
            const responseArray = JSON.parse(res.text());
            console.log('RemoveLike Response: ' + JSON.stringify(responseArray));
            if (responseArray.likeStatus != null) {
                statusPackage.setStatus(responseArray.likeStatus.status);
                statusPackage.setMessage(responseArray.likeStatus.message);
                if (statusPackage.getStatus()) {
                    responsePackage.setUserId(responseArray.userId);
                    responsePackage.setShopId(responseArray.shopId);
                }
            }
        } else {
            console.log('abnormal response' + res);
        }
        responsePackage.setLikeStatus(statusPackage);
        console.log('ShopManagementService::extractRemoveLikeShopData [EXIT]');
        return responsePackage;
    }


    /**
     * Parses the received response
     * and returns a response package
     */
    private extractProcessLikeShopData(res: Response): ShopLikeResponse {
        const responsePackage: ShopLikeResponse = new ShopLikeResponse();
        const statusPackage: ShopLikeStatus = new ShopLikeStatus();
        console.log('Auth Connection Attempt successful\nShopLikeResponse obtained from Shop Service');
        if (res.status === 200 && res.statusText === 'OK') {
            const responseArray = JSON.parse(res.text());
            console.log('ProcessLike Response: ' + JSON.stringify(responseArray));
            if (responseArray.likeStatus != null) {
                statusPackage.setStatus(responseArray.likeStatus.status);
                statusPackage.setMessage(responseArray.likeStatus.message);
                if (statusPackage.getStatus()) {
                    responsePackage.setUserId(responseArray.userId);
                    responsePackage.setShopId(responseArray.shopId);
                    responsePackage.setLikeType(responseArray.likeType);
                }
            }
        } else {
            console.log('abnormal response' + res);
        }
        responsePackage.setLikeStatus(statusPackage);
        console.log('ShopManagementService::login [EXIT]');
        return responsePackage;
    }

    public getNearbyShops(request: PreferredShopsRequest): Observable<PreferredShopsResponse> {
        console.log('ShopManagementService::getAllShops [ENTER]');
        if (request != null && request.getUsername() != null) {
            console.log('Connecting to Shop Service...');
            // making a get request to Shop Service
            return this.http.post(this._url_shop_nearbyShops, request, this.getOptions())
                .map(data => this.extractPreferredShopData(data))
                .catch(this.errorhandler.handleError);
        }
    }

    public getPreferredShops(request: PreferredShopsRequest): Observable<PreferredShopsResponse> {
        console.log('ShopManagementService::getAllShops [ENTER]');
        if (request != null && request.getUsername() != null) {
            console.log('Connecting to Shop Service...');
            // making a get request to Shop Service
            return this.http.post(this._url_shop_preferredShops, request, this.getOptions())
                .map(data => this.extractPreferredShopData(data))
                .catch(this.errorhandler.handleError);
        }
    }

    /**
     * Get the response data for register request
     *
     */
    private extractPreferredShopData(res: Response): PreferredShopsResponse {
        // create instances of GetAllShopsResponse
        const responsePackage: PreferredShopsResponse = new PreferredShopsResponse();
        const statusPackage: PreferredShopsStatus = new PreferredShopsStatus();
        console.log('Connection Attempt successful\nPreferredShopsResponse obtained from Shop Service');
        // check if response has HttpStatus 200
        if (res.status === 200 && res.statusText === 'OK') {
            // create an array out of response body
            const responseArray = JSON.parse(res.text());
            // console.log('Register Response: ' + JSON.stringify(responseArray));
            if (responseArray.preferredShopsStatus != null) {
                statusPackage.setStatus(responseArray.preferredShopsStatus.status);
                statusPackage.setMessage(responseArray.preferredShopsStatus.message);
                if (statusPackage.getStatus()) {
                    // gets list of Shops
                    const daoShopList: DaoShop[] = [];
                    const shopArray = responseArray.shops;
                    // traverses the Shop Array  in response
                    for (let i = 0; i < shopArray.length; i++) {
                        // get location from response
                        const daoShopLocation: DaoShopLocation = new DaoShopLocation();
                        daoShopLocation.setCoordinates(shopArray[i].location.coordinates);
                        daoShopLocation.setType(shopArray[i].location.type);

                        // get all the other fields of DaoShop from response
                        const daoShop: DaoShop = new DaoShop();
                        daoShop.set_id(shopArray[i]._id);
                        daoShop.setCity(shopArray[i].city);
                        daoShop.setEmail(shopArray[i].email);
                        daoShop.setName(shopArray[i].name);
                        daoShop.setPicture(shopArray[i].picture);
                        daoShop.setLocation(daoShopLocation);

                        // push extracted shop to the Shop Array
                        daoShopList.push(daoShop);
                    }
                    responsePackage.setShops(daoShopList);
                }
            }
        } else {
            console.log('abnormal response' + res);
        }
        responsePackage.setPreferredShopsStatus(statusPackage);
        console.log('ShopManagementService::extractPreferredShopData [EXIT]');
        return responsePackage;
    }
}
