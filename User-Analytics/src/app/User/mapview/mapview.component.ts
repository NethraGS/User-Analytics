import { Component, OnInit, ViewChild, ElementRef, AfterViewInit, OnDestroy } from '@angular/core';
import * as L from 'leaflet';
import { Map, NavigationControl, Marker } from 'maplibre-gl';

@Component({
  selector: 'app-mapview',
  standalone: true,
  imports: [],
  templateUrl: './mapview.component.html',
  styleUrls: ['./mapview.component.css']
})
export class MapviewComponent implements OnInit, AfterViewInit, OnDestroy {
  map: Map | undefined;

  @ViewChild('map')
  private mapContainer!: ElementRef<HTMLElement>;

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    // Set the initial coordinates to Chennai, India
    const initialState = { lng: 80.2209, lat: 13.0105, zoom: 14 };  // Chennai, India

    // Initialize the map with MapTiler style and centered at Chennai
    this.map = new Map({
      container: this.mapContainer.nativeElement,
      style: `https://api.maptiler.com/maps/streets-v2/style.json?key=KYF9wgI47DOeYBVqtdI5`,
      center: [initialState.lng, initialState.lat],
      zoom: initialState.zoom
    });

    // Add navigation controls to the map
    this.map.addControl(new NavigationControl(), 'top-right');

    // Add a marker for "Dhyan Networks and Technologies" in Guindy, Chennai
    const dhyanNetworksCoordinates: [number, number] = [80.2209, 13.0105]; // Dhyan Networks, Guindy, Chennai
    new Marker({ color: "#FF0000" })
      .setLngLat(dhyanNetworksCoordinates)  // This now has the correct tuple type
      .addTo(this.map);
  }

  ngOnDestroy() {
    // Clean up the map instance when the component is destroyed
    this.map?.remove();
  }
}
