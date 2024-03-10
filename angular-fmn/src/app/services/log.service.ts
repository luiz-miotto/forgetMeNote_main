import { Injectable } from '@angular/core';
@Injectable()

export class LogService {

  constructor() {
  }

  log(msg: any) {
    console.log(new Date() + ": " + JSON.stringify(msg));
  }
}
