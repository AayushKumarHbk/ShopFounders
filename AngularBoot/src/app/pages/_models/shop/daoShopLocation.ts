export class DaoShopLocation {

    private type: string;
    private coordinates: any[];

    public getType(): string {
        return this.type;
    }

    public setType(type: string) {
        this.type = type;
    }

    public getCoordinates(): any[] {
        return this.coordinates;
    }

    public setCoordinates(coordinates: any[]) {
        this.coordinates = coordinates;
    }
}
