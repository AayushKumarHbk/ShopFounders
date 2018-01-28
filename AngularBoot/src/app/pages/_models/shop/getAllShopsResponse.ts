import { DaoShop } from './daoShop';
import { GetAllShopsStatus } from './getAllShopsStatus';

export class GetAllShopsResponse {

    private shops: DaoShop[];
    private getAllShopsStatus: GetAllShopsStatus;

    public getShops(): DaoShop[] {
        return this.shops;
    }

    public setShops(shops: DaoShop[]) {
        this.shops = shops;
    }

    public getGetAllShopsStatus(): GetAllShopsStatus {
        return this.getAllShopsStatus;
    }

    public setGetAllShopsStatus(getAllShopsStatus: GetAllShopsStatus) {
        this.getAllShopsStatus = getAllShopsStatus;
    }
}
