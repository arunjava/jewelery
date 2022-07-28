import { Component, OnInit } from '@angular/core';
import { OfferServiceService } from 'src/app/service/offer-service/offer-service.service';
import { Offer } from '../../../../models/offers/Offer.model';

@Component({
  selector: 'app-offers-view',
  templateUrl: './offers-view.component.html',
  styleUrls: ['./offers-view.component.css']
})
export class OffersViewComponent implements OnInit {

  offers = {} as Array<Offer>;
  filteredOffers = {} as Array<Offer>;
  page = 1;
  pageSize = 5;
  collectionSize = 0;

  constructor(
    private offerService: OfferServiceService
  ) { }

  ngOnInit(): void {
    this.offerService.getAllOffer().subscribe(response => {
      this.offers = response.result;
      this.collectionSize = this.offers.length;
      this.refreshOffers();
    });
  }

  refreshOffers() {
    this.filteredOffers = this.offers.slice((this.page - 1) * this.pageSize, (this.page - 1) * this.pageSize + this.pageSize);
  }


}
