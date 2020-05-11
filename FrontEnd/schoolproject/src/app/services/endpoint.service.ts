import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EndpointService {

  private points = { 'api_microservices': 'http://localhost:8090/api/', 'apiSMS': 'http://192.168.230.23:9080/sendMessage' }

  constructor() { }
  
  getEndPoints() {
    return this.points;
  }
}
