export interface IPonudjaci {
  id?: number;
  ponudjac?: string;
  kontakt?: string;
  adresa?: string;
  grad?: string;
  telefon?: string;
  email?: string;
  postanskibroj?: string;
  pib?: string;
  fax?: string;
  web?: string;
}

export class Ponudjaci implements IPonudjaci {
  constructor(
    public id?: number,
    public ponudjac?: string,
    public kontakt?: string,
    public adresa?: string,
    public grad?: string,
    public telefon?: string,
    public email?: string,
    public postanskibroj?: string,
    public pib?: string,
    public fax?: string,
    public web?: string
  ) {}
}
