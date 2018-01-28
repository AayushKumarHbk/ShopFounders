import {DaoShopLocation} from './daoShopLocation';

export class DaoShop {

    private _id: string;
    private picture: string;
    private name: string;
    private email: string;
    private city: string;
    private location: DaoShopLocation;

    public constructor() { }

    public get_id(): string {
        return this._id;
    }

    public set_id(_id: string) {
        this._id = _id;
    }

    public getPicture(): string {
        return this.picture;
    }

    public setPicture(picture: string) {
        this.picture = picture;
    }

    public getName(): string {
        return name;
    }

    public setName(name: string) {
        this.name = name;
    }

    public getEmail(): string {
        return this.email;
    }

    public setEmail(email: string) {
        this.email = email;
    }

    public getCity(): string {
        return this.city;
    }

    public setCity(city: string) {
        this.city = city;
    }

    public getLocation(): DaoShopLocation {
        return this.location;
    }

    public setLocation(location: DaoShopLocation) {
        this.location = location;
    }
}
