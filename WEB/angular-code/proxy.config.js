const proxy = [
  {
    context: '/CMS-ACQUIRER-DXC-INCOMING-ELO',
    target: 'http://localhost:8080'
    // target: 'http://hom04.cardsutility.eds.com.br'
  }
];
module.exports = proxy;