import { Component, OnInit } from '@angular/core';
import { ChartConfiguration } from 'chart.js';
import { EquipementService } from '../services/equipement.service';

@Component({
  selector: 'app-statistiques-equipement',
  templateUrl: './statistiques-equipement.component.html'
})
export class StatistiquesEquipementComponent implements OnInit {
  stats: { [key: string]: number } = {};
  labels: string[] = [];
  data: number[] = [];

  chartConfig: ChartConfiguration<'doughnut'> = {
    type: 'doughnut',
    data: {
      labels: [],
      datasets: [
        {
          data: [],
          backgroundColor: ['#28a745', '#ffc107', '#dc3545'], // Vert, Jaune, Rouge
          hoverOffset: 8
        }
      ]
    },
    options: {
      responsive: true,
      plugins: {
        legend: {
          position: 'bottom',
          labels: {
            color: '#333',
            font: {
              size: 14
            }
          }
        }
      }
    }
  };

  constructor(private equipementService: EquipementService) {}

  ngOnInit() {
    this.equipementService.getStatistiquesParEtat().subscribe(data => {
      this.stats = data;
      this.labels = Object.keys(data);
      this.data = Object.values(data);

      this.chartConfig.data.labels = this.labels;
      this.chartConfig.data.datasets[0].data = this.data;
    });
  }
}

