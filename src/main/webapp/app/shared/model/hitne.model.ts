import { Moment } from 'moment';
import { IPonudjaci } from 'app/shared/model/ponudjaci.model';

export interface IHitne {
  id?: number;
  brojpokretanja?: number;
  datumpokretanja?: Moment;
  vrijednost?: number;
  naziv?: string;
  ponudjaci?: IPonudjaci;
}

export class Hitne implements IHitne {
  constructor(
    public id?: number,
    public brojpokretanja?: number,
    public datumpokretanja?: Moment,
    public vrijednost?: number,
    public naziv?: string,
    public ponudjaci?: IPonudjaci
  ) {}
}
