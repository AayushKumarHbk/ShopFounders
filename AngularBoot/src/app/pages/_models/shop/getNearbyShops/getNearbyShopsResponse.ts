import { DaoShop } from '../daoShop';
import { NearbyShopsStatus } from './nearbyShopsStatus';

export class NearbyShopsResponse {

    private shops: DaoShop[];
    private nearbyShopsStatus: NearbyShopsStatus;

    public getShops(): DaoShop[] {
        return this.shops;
    }

    public setShops(shops: DaoShop[]) {
        this.shops = shops;
    }

    public getNearbyShopsStatus(): NearbyShopsStatus {
        return this.nearbyShopsStatus;
    }

    public setNearbyShopsStatus(nearbyShopsStatus: NearbyShopsStatus) {
        this.nearbyShopsStatus = nearbyShopsStatus;
    }
}
