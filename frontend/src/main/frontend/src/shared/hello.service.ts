import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class HelloService {
	constructor(
		private http: Http
		) {}

	getHello() {
		return this.http.get("/api/hello")
			.map((res: Response) => res.json());
	}
}