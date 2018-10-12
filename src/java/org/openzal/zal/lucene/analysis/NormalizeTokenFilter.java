package org.openzal.zal.lucene.analysis;

import java.io.IOException;

public final class NormalizeTokenFilter extends CharFilter
{

  public NormalizeTokenFilter(CharStream in)
  {
    super(in);
  }


  public NormalizeTokenFilter(Object zObject)
  {
    /* $if ZimbraVersion >= 8.7.0 $ */
    super((com.zimbra.cs.index.analysis.NormalizeTokenFilter)zObject);
    /* $else $
    super(null);
    /* $endif $ */
  }

  @Override
  protected com.zimbra.cs.index.analysis.NormalizeTokenFilter getZimbra()
  {
    /* $if ZimbraVersion >= 8.7.0 $ */
    return (com.zimbra.cs.index.analysis.NormalizeTokenFilter) super.getZimbra();
    /* $else $
    throw new UnsupportedOperationException();
    /* $endif $ */
  }


  public int read()
    throws IOException
  {
    /* $if ZimbraVersion >= 8.7.0 $
    return getZimbra().read();
    /* $else $ */
    return normalize(super.read());
    /* $endif $ */
  }

  public int read(char[] buf, int offset, int len)
    throws IOException
  {
    /* $if ZimbraVersion >= 8.7.0 $
    return getZimbra().read(buf, offset, len);
    /* $else $ */
    int result = super.read(buf, offset, len);

    for( int i = 0; i < result; ++i )
    {
      buf[offset + i] = (char) normalize(buf[offset + i]);
    }

    return result;
    /* $endif $ */
  }

  public static int normalize(int c)
  {
    /* $if ZimbraVersion >= 8.7.0 $
    return com.zimbra.cs.index.analysis.NormalizeTokenFilter.normalize(c);
    /* $else $ */
    return normalize(c, 0);
    /* $endif $ */
  }

  public static int normalize(int c, int p)
  {
    /* $if ZimbraVersion >= 8.7.0 $
    return com.zimbra.cs.index.analysis.NormalizeTokenFilter.normalize(c, p);
    /* $else $ */
    switch( c )
    {
      case 178:
      case 8322:
      case 9313:
      case 9462:
      case 10103:
      case 10113:
      case 10123:
      case 65298:
        return 50;
      case 179:
      case 8323:
      case 9314:
      case 9463:
      case 10104:
      case 10114:
      case 10124:
      case 65299:
        return 51;
      case 185:
      case 8321:
      case 9312:
      case 9461:
      case 10102:
      case 10112:
      case 10122:
      case 65297:
        return 49;
      case 192:
      case 193:
      case 194:
      case 195:
      case 196:
      case 197:
      case 224:
      case 225:
      case 226:
      case 227:
      case 228:
      case 229:
      case 256:
      case 257:
      case 258:
      case 259:
      case 260:
      case 261:
      case 399:
      case 461:
      case 462:
      case 478:
      case 479:
      case 480:
      case 481:
      case 506:
      case 507:
      case 512:
      case 513:
      case 514:
      case 515:
      case 550:
      case 551:
      case 570:
      case 592:
      case 601:
      case 602:
      case 7424:
      case 7567:
      case 7573:
      case 7680:
      case 7681:
      case 7834:
      case 7840:
      case 7841:
      case 7842:
      case 7843:
      case 7844:
      case 7845:
      case 7846:
      case 7847:
      case 7848:
      case 7849:
      case 7850:
      case 7851:
      case 7852:
      case 7853:
      case 7854:
      case 7855:
      case 7856:
      case 7857:
      case 7858:
      case 7859:
      case 7860:
      case 7861:
      case 7862:
      case 7863:
      case 8336:
      case 8340:
      case 9398:
      case 9424:
      case 11365:
      case 11375:
      case 65313:
      case 65345:
        return 97;
      case 199:
      case 231:
      case 262:
      case 263:
      case 264:
      case 265:
      case 266:
      case 267:
      case 268:
      case 269:
      case 391:
      case 392:
      case 571:
      case 572:
      case 597:
      case 663:
      case 7428:
      case 7688:
      case 7689:
      case 8580:
      case 9400:
      case 9426:
      case 42814:
      case 42815:
      case 65315:
      case 65347:
        return 99;
      case 200:
      case 201:
      case 202:
      case 203:
      case 232:
      case 233:
      case 234:
      case 235:
      case 274:
      case 275:
      case 276:
      case 277:
      case 278:
      case 279:
      case 280:
      case 281:
      case 282:
      case 283:
      case 398:
      case 400:
      case 477:
      case 516:
      case 517:
      case 518:
      case 519:
      case 552:
      case 553:
      case 582:
      case 583:
      case 600:
      case 603:
      case 604:
      case 605:
      case 606:
      case 666:
      case 7431:
      case 7432:
      case 7570:
      case 7571:
      case 7572:
      case 7700:
      case 7701:
      case 7702:
      case 7703:
      case 7704:
      case 7705:
      case 7706:
      case 7707:
      case 7708:
      case 7709:
      case 7864:
      case 7865:
      case 7866:
      case 7867:
      case 7868:
      case 7869:
      case 7870:
      case 7871:
      case 7872:
      case 7873:
      case 7874:
      case 7875:
      case 7876:
      case 7877:
      case 7878:
      case 7879:
      case 8337:
      case 9402:
      case 9428:
      case 11384:
      case 11387:
      case 65317:
      case 65349:
        return 101;
      case 204:
      case 205:
      case 206:
      case 207:
      case 236:
      case 237:
      case 238:
      case 239:
      case 296:
      case 297:
      case 298:
      case 299:
      case 300:
      case 301:
      case 302:
      case 303:
      case 304:
      case 305:
      case 406:
      case 407:
      case 463:
      case 464:
      case 520:
      case 521:
      case 522:
      case 523:
      case 616:
      case 618:
      case 7433:
      case 7522:
      case 7547:
      case 7548:
      case 7574:
      case 7724:
      case 7725:
      case 7726:
      case 7727:
      case 7880:
      case 7881:
      case 7882:
      case 7883:
      case 8305:
      case 9406:
      case 9432:
      case 43006:
      case 65321:
      case 65353:
        return 105;
      case 208:
      case 240:
      case 270:
      case 271:
      case 272:
      case 273:
      case 393:
      case 394:
      case 395:
      case 396:
      case 545:
      case 598:
      case 599:
      case 7429:
      case 7430:
      case 7533:
      case 7553:
      case 7569:
      case 7690:
      case 7691:
      case 7692:
      case 7693:
      case 7694:
      case 7695:
      case 7696:
      case 7697:
      case 7698:
      case 7699:
      case 9401:
      case 9427:
      case 42873:
      case 42874:
      case 65316:
      case 65348:
        return 100;
      case 209:
      case 241:
      case 323:
      case 324:
      case 325:
      case 326:
      case 327:
      case 328:
      case 329:
      case 330:
      case 331:
      case 413:
      case 414:
      case 504:
      case 505:
      case 544:
      case 565:
      case 626:
      case 627:
      case 628:
      case 7438:
      case 7536:
      case 7559:
      case 7748:
      case 7749:
      case 7750:
      case 7751:
      case 7752:
      case 7753:
      case 7754:
      case 7755:
      case 8319:
      case 9411:
      case 9437:
      case 65326:
      case 65358:
        return 110;
      case 210:
      case 211:
      case 212:
      case 213:
      case 214:
      case 216:
      case 242:
      case 243:
      case 244:
      case 245:
      case 246:
      case 248:
      case 332:
      case 333:
      case 334:
      case 335:
      case 336:
      case 337:
      case 390:
      case 415:
      case 416:
      case 417:
      case 465:
      case 466:
      case 490:
      case 491:
      case 492:
      case 493:
      case 510:
      case 511:
      case 524:
      case 525:
      case 526:
      case 527:
      case 554:
      case 555:
      case 556:
      case 557:
      case 558:
      case 559:
      case 560:
      case 561:
      case 596:
      case 629:
      case 7439:
      case 7440:
      case 7446:
      case 7447:
      case 7575:
      case 7756:
      case 7757:
      case 7758:
      case 7759:
      case 7760:
      case 7761:
      case 7762:
      case 7763:
      case 7884:
      case 7885:
      case 7886:
      case 7887:
      case 7888:
      case 7889:
      case 7890:
      case 7891:
      case 7892:
      case 7893:
      case 7894:
      case 7895:
      case 7896:
      case 7897:
      case 7898:
      case 7899:
      case 7900:
      case 7901:
      case 7902:
      case 7903:
      case 7904:
      case 7905:
      case 7906:
      case 7907:
      case 8338:
      case 9412:
      case 9438:
      case 11386:
      case 42826:
      case 42827:
      case 42828:
      case 42829:
      case 65327:
      case 65359:
        return 111;
      case 217:
      case 218:
      case 219:
      case 220:
      case 249:
      case 250:
      case 251:
      case 252:
      case 360:
      case 361:
      case 362:
      case 363:
      case 364:
      case 365:
      case 366:
      case 367:
      case 368:
      case 369:
      case 370:
      case 371:
      case 431:
      case 432:
      case 467:
      case 468:
      case 469:
      case 470:
      case 471:
      case 472:
      case 473:
      case 474:
      case 475:
      case 476:
      case 532:
      case 533:
      case 534:
      case 535:
      case 580:
      case 649:
      case 7452:
      case 7524:
      case 7550:
      case 7577:
      case 7794:
      case 7795:
      case 7796:
      case 7797:
      case 7798:
      case 7799:
      case 7800:
      case 7801:
      case 7802:
      case 7803:
      case 7908:
      case 7909:
      case 7910:
      case 7911:
      case 7912:
      case 7913:
      case 7914:
      case 7915:
      case 7916:
      case 7917:
      case 7918:
      case 7919:
      case 7920:
      case 7921:
      case 9418:
      case 9444:
      case 65333:
      case 65365:
        return 117;
      case 221:
      case 253:
      case 255:
      case 374:
      case 375:
      case 376:
      case 435:
      case 436:
      case 562:
      case 563:
      case 590:
      case 591:
      case 654:
      case 655:
      case 7822:
      case 7823:
      case 7833:
      case 7922:
      case 7923:
      case 7924:
      case 7925:
      case 7926:
      case 7927:
      case 7928:
      case 7929:
      case 7934:
      case 7935:
      case 9422:
      case 9448:
      case 65337:
      case 65369:
        return 121;
      case 284:
      case 285:
      case 286:
      case 287:
      case 288:
      case 289:
      case 290:
      case 291:
      case 403:
      case 484:
      case 485:
      case 486:
      case 487:
      case 500:
      case 501:
      case 608:
      case 609:
      case 610:
      case 667:
      case 7543:
      case 7545:
      case 7555:
      case 7712:
      case 7713:
      case 9404:
      case 9430:
      case 42877:
      case 42878:
      case 42879:
      case 65319:
      case 65351:
        return 103;
      case 292:
      case 293:
      case 294:
      case 295:
      case 542:
      case 543:
      case 613:
      case 614:
      case 668:
      case 686:
      case 687:
      case 7714:
      case 7715:
      case 7716:
      case 7717:
      case 7718:
      case 7719:
      case 7720:
      case 7721:
      case 7722:
      case 7723:
      case 7830:
      case 9405:
      case 9431:
      case 11367:
      case 11368:
      case 11381:
      case 11382:
      case 65320:
      case 65352:
        return 104;
      case 308:
      case 309:
      case 496:
      case 567:
      case 584:
      case 585:
      case 607:
      case 644:
      case 669:
      case 7434:
      case 9407:
      case 9433:
      case 11388:
      case 65322:
      case 65354:
        return 106;
      case 310:
      case 311:
      case 408:
      case 409:
      case 488:
      case 489:
      case 670:
      case 7435:
      case 7556:
      case 7728:
      case 7729:
      case 7730:
      case 7731:
      case 7732:
      case 7733:
      case 9408:
      case 9434:
      case 11369:
      case 11370:
      case 42816:
      case 42817:
      case 42818:
      case 42819:
      case 42820:
      case 42821:
      case 65323:
      case 65355:
        return 107;
      case 312:
      case 586:
      case 587:
      case 672:
      case 9414:
      case 9440:
      case 42838:
      case 42839:
      case 42840:
      case 42841:
      case 65329:
      case 65361:
        return 113;
      case 313:
      case 314:
      case 315:
      case 316:
      case 317:
      case 318:
      case 319:
      case 320:
      case 321:
      case 322:
      case 410:
      case 564:
      case 573:
      case 619:
      case 620:
      case 621:
      case 671:
      case 7436:
      case 7557:
      case 7734:
      case 7735:
      case 7736:
      case 7737:
      case 7738:
      case 7739:
      case 7740:
      case 7741:
      case 9409:
      case 9435:
      case 11360:
      case 11361:
      case 11362:
      case 42822:
      case 42823:
      case 42824:
      case 42825:
      case 42880:
      case 42881:
      case 65324:
      case 65356:
        return 108;
      case 340:
      case 341:
      case 342:
      case 343:
      case 344:
      case 345:
      case 528:
      case 529:
      case 530:
      case 531:
      case 588:
      case 589:
      case 636:
      case 637:
      case 638:
      case 639:
      case 640:
      case 641:
      case 7449:
      case 7450:
      case 7523:
      case 7538:
      case 7539:
      case 7561:
      case 7768:
      case 7769:
      case 7770:
      case 7771:
      case 7772:
      case 7773:
      case 7774:
      case 7775:
      case 9415:
      case 9441:
      case 11364:
      case 42842:
      case 42843:
      case 42882:
      case 42883:
      case 65330:
      case 65362:
        return 114;
      case 346:
      case 347:
      case 348:
      case 349:
      case 350:
      case 351:
      case 352:
      case 353:
      case 383:
      case 536:
      case 537:
      case 575:
      case 642:
      case 7540:
      case 7562:
      case 7776:
      case 7777:
      case 7778:
      case 7779:
      case 7780:
      case 7781:
      case 7782:
      case 7783:
      case 7784:
      case 7785:
      case 7836:
      case 7837:
      case 9416:
      case 9442:
      case 42801:
      case 42884:
      case 42885:
      case 65331:
      case 65363:
        return 115;
      case 354:
      case 355:
      case 356:
      case 357:
      case 358:
      case 359:
      case 427:
      case 428:
      case 429:
      case 430:
      case 538:
      case 539:
      case 566:
      case 574:
      case 647:
      case 648:
      case 7451:
      case 7541:
      case 7786:
      case 7787:
      case 7788:
      case 7789:
      case 7790:
      case 7791:
      case 7792:
      case 7793:
      case 7831:
      case 9417:
      case 9443:
      case 11366:
      case 42886:
      case 65332:
      case 65364:
        return 116;
      case 372:
      case 373:
      case 447:
      case 503:
      case 653:
      case 7457:
      case 7808:
      case 7809:
      case 7810:
      case 7811:
      case 7812:
      case 7813:
      case 7814:
      case 7815:
      case 7816:
      case 7817:
      case 7832:
      case 9420:
      case 9446:
      case 11378:
      case 11379:
      case 65335:
      case 65367:
        return 119;
      case 377:
      case 378:
      case 379:
      case 380:
      case 381:
      case 382:
      case 437:
      case 438:
      case 540:
      case 541:
      case 548:
      case 549:
      case 576:
      case 656:
      case 657:
      case 7458:
      case 7542:
      case 7566:
      case 7824:
      case 7825:
      case 7826:
      case 7827:
      case 7828:
      case 7829:
      case 9423:
      case 9449:
      case 11371:
      case 11372:
      case 42850:
      case 42851:
      case 65338:
      case 65370:
        return 122;
      case 384:
      case 385:
      case 386:
      case 387:
      case 579:
      case 595:
      case 665:
      case 7427:
      case 7532:
      case 7552:
      case 7682:
      case 7683:
      case 7684:
      case 7685:
      case 7686:
      case 7687:
      case 9399:
      case 9425:
      case 65314:
      case 65346:
        return 98;
      case 401:
      case 402:
      case 7534:
      case 7554:
      case 7710:
      case 7711:
      case 7835:
      case 9403:
      case 9429:
      case 42800:
      case 42875:
      case 42876:
      case 43003:
      case 65318:
      case 65350:
        return 102;
      case 412:
      case 623:
      case 624:
      case 625:
      case 7437:
      case 7535:
      case 7558:
      case 7742:
      case 7743:
      case 7744:
      case 7745:
      case 7746:
      case 7747:
      case 9410:
      case 9436:
      case 11374:
      case 43005:
      case 43007:
      case 65325:
      case 65357:
        return 109;
      case 420:
      case 421:
      case 7448:
      case 7537:
      case 7549:
      case 7560:
      case 7764:
      case 7765:
      case 7766:
      case 7767:
      case 9413:
      case 9439:
      case 11363:
      case 42832:
      case 42833:
      case 42834:
      case 42835:
      case 42836:
      case 42837:
      case 43004:
      case 65328:
      case 65360:
        return 112;
      case 434:
      case 581:
      case 651:
      case 652:
      case 7456:
      case 7525:
      case 7564:
      case 7804:
      case 7805:
      case 7806:
      case 7807:
      case 7932:
      case 9419:
      case 9445:
      case 11377:
      case 11380:
      case 42846:
      case 42847:
      case 42856:
      case 65334:
      case 65366:
        return 118;
      case 7565:
      case 7818:
      case 7819:
      case 7820:
      case 7821:
      case 8339:
      case 9421:
      case 9447:
      case 65336:
      case 65368:
        return 120;
      case 8304:
      case 8320:
      case 9450:
      case 9471:
      case 65296:
        return 48;
      case 8308:
      case 8324:
      case 9315:
      case 9464:
      case 10105:
      case 10115:
      case 10125:
      case 65300:
        return 52;
      case 8309:
      case 8325:
      case 9316:
      case 9465:
      case 10106:
      case 10116:
      case 10126:
      case 65301:
        return 53;
      case 8310:
      case 8326:
      case 9317:
      case 9466:
      case 10107:
      case 10117:
      case 10127:
      case 65302:
        return 54;
      case 8311:
      case 8327:
      case 9318:
      case 9467:
      case 10108:
      case 10118:
      case 10128:
      case 65303:
        return 55;
      case 8312:
      case 8328:
      case 9319:
      case 9468:
      case 10109:
      case 10119:
      case 10129:
      case 65304:
        return 56;
      case 8313:
      case 8329:
      case 9320:
      case 9469:
      case 10110:
      case 10120:
      case 10130:
      case 65305:
        return 57;
      case 12449:
      case 65383:
        return 12353;
      case 12450:
      case 65393:
        return 12354;
      case 12451:
      case 65384:
        return 12355;
      case 12452:
      case 65394:
        return 12356;
      case 12453:
      case 65385:
        return 12357;
      case 12455:
      case 65386:
        return 12359;
      case 12456:
      case 65396:
        return 12360;
      case 12457:
      case 65387:
        return 12361;
      case 12458:
      case 65397:
        return 12362;
      case 12460:
        return 12364;
      case 12462:
        return 12366;
      case 12464:
        return 12368;
      case 12466:
        return 12370;
      case 12468:
        return 12372;
      case 12470:
        return 12374;
      case 12472:
        return 12376;
      case 12474:
        return 12378;
      case 12476:
        return 12380;
      case 12478:
        return 12382;
      case 12480:
        return 12384;
      case 12482:
        return 12386;
      case 12483:
      case 65391:
        return 12387;
      case 12485:
        return 12389;
      case 12487:
        return 12391;
      case 12489:
        return 12393;
      case 12490:
      case 65413:
        return 12394;
      case 12491:
      case 65414:
        return 12395;
      case 12492:
      case 65415:
        return 12396;
      case 12493:
      case 65416:
        return 12397;
      case 12494:
      case 65417:
        return 12398;
      case 12496:
        return 12400;
      case 12497:
        return 12401;
      case 12499:
        return 12403;
      case 12500:
        return 12404;
      case 12502:
        return 12406;
      case 12503:
        return 12407;
      case 12505:
        return 12409;
      case 12506:
        return 12410;
      case 12508:
        return 12412;
      case 12509:
        return 12413;
      case 12510:
      case 65423:
        return 12414;
      case 12511:
      case 65424:
        return 12415;
      case 12512:
      case 65425:
        return 12416;
      case 12513:
      case 65426:
        return 12417;
      case 12514:
      case 65427:
        return 12418;
      case 12515:
      case 65388:
        return 12419;
      case 12516:
      case 65428:
        return 12420;
      case 12517:
      case 65389:
        return 12421;
      case 12518:
      case 65429:
        return 12422;
      case 12519:
      case 65390:
        return 12423;
      case 12520:
      case 65430:
        return 12424;
      case 12521:
      case 65431:
        return 12425;
      case 12522:
      case 65432:
        return 12426;
      case 12523:
      case 65433:
        return 12427;
      case 12524:
      case 65434:
        return 12428;
      case 12525:
      case 65435:
        return 12429;
      case 12526:
        return 12430;
      case 12527:
      case 65436:
        return 12431;
      case 12529:
        return 12433;
      case 12530:
      case 65382:
        return 12434;
      case 12531:
      case 65437:
        return 12435;
      case 12532:
        return 12436;
      case 12533:
        return 12437;
      case 12534:
        return 12438;
      case 65392:
        return 12540;
      case 65395:
        if( p == 65438 )
        {
          return 12436;
        }
      case 12454:
        return 12358;
      case 65398:
        if( p == 65438 )
        {
          return 12364;
        }
      case 12459:
        return 12363;
      case 65399:
        if( p == 65438 )
        {
          return 12366;
        }
      case 12461:
        return 12365;
      case 65400:
        if( p == 65438 )
        {
          return 12368;
        }
      case 12463:
        return 12367;
      case 65401:
        if( p == 65438 )
        {
          return 12370;
        }
      case 12465:
        return 12369;
      case 65402:
        if( p == 65438 )
        {
          return 12372;
        }
      case 12467:
        return 12371;
      case 65403:
        if( p == 65438 )
        {
          return 12374;
        }
      case 12469:
        return 12373;
      case 65404:
        if( p == 65438 )
        {
          return 12376;
        }
      case 12471:
        return 12375;
      case 65405:
        if( p == 65438 )
        {
          return 12378;
        }
      case 12473:
        return 12377;
      case 65406:
        if( p == 65438 )
        {
          return 12380;
        }
      case 12475:
        return 12379;
      case 65407:
        if( p == 65438 )
        {
          return 12382;
        }
      case 12477:
        return 12381;
      case 65408:
        if( p == 65438 )
        {
          return 12384;
        }
      case 12479:
        return 12383;
      case 65409:
        if( p == 65438 )
        {
          return 12386;
        }
      case 12481:
        return 12385;
      case 65410:
        if( p == 65438 )
        {
          return 12389;
        }
      case 12484:
        return 12388;
      case 65411:
        if( p == 65438 )
        {
          return 12391;
        }
      case 12486:
        return 12390;
      case 65412:
        if( p == 65438 )
        {
          return 12393;
        }
      case 12488:
        return 12392;
      case 65418:
        switch( p )
        {
          case 65438:
            return 12400;
          case 65439:
            return 12401;
        }
      case 12495:
        return 12399;
      case 65419:
        switch( p )
        {
          case 65438:
            return 12403;
          case 65439:
            return 12404;
        }
      case 12498:
        return 12402;
      case 65420:
        switch( p )
        {
          case 65438:
            return 12406;
          case 65439:
            return 12407;
        }
      case 12501:
        return 12405;
      case 65421:
        switch( p )
        {
          case 65438:
            return 12409;
          case 65439:
            return 12410;
        }
      case 12504:
        return 12408;
      case 65422:
        switch( p )
        {
          case 65438:
            return 12412;
          case 65439:
            return 12413;
        }
      case 12507:
        return 12411;
      case 65438:
        return 12441;
      case 65439:
        return 12442;
      default:
        return Character.toLowerCase(c);
    }
    /* $endif $ */
  }

  public static String normalize(String value)
  {
    /* $if ZimbraVersion >= 8.7.0 $
    return com.zimbra.cs.index.analysis.NormalizeTokenFilter.normalize(value);
    /* $else $ */
    StringBuilder result = new StringBuilder();

    for( int i = 0; i < value.length(); ++i )
    {
      result.append((char) normalize(value.charAt(i), i + 1 < value.length() ? value.charAt(i + 1) : 0));
    }

    return result.toString();
    /* $endif $ */
  }
}
