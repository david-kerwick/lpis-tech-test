export class DealClass {

  constructor(
    public id: number,
    public retention: number,
    public limit: number,
    public perils: Array<number>,
    public regions: Array<number>
  ) {
  }
}
