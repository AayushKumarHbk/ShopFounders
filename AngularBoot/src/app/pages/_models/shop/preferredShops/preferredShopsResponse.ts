import { PreferredShopsStatus } from './preferredShopsStatus';
import { DaoShop } from '../daoShop';

export class PreferredShopsResponse {

    private shops: DaoShop[];
    private preferredShopsStatus: PreferredShopsStatus;

    public getShops(): DaoShop[] {
        return this.shops;
    }

    public setShops(shops: DaoShop[]) {
        this.shops = shops;
    }

    public getPreferredShopsStatus(): PreferredShopsStatus {
        return this.preferredShopsStatus;
    }

    public setPreferredShopsStatus(preferredShopsStatus: PreferredShopsStatus) {
        this.preferredShopsStatus = preferredShopsStatus;
    }
}
