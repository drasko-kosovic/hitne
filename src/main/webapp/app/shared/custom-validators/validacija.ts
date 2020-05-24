import { AbstractControl, ValidationErrors } from '@angular/forms';

export class Validacija {
  static cannotContainSpace(control: AbstractControl): ValidationErrors | null {
    if ((control.value as string).indexOf('') >= 0) return { cannotControlSpace: true };
    return null;
  }
}
