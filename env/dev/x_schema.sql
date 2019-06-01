DROP TABLE DTBL_USER_LOGIN;
CREATE TABLE DTBL_USER_LOGIN (
BRCD VARCHAR(3) NOT NULL,
EMAIL VARCHAR(100) NOT NULL,
ROL VARCHAR(1),
CREATEDATE VARCHAR(19),
PRIMARY KEY (BRCD,EMAIL));

DROP TABLE DTBL_CUS_ORDER;
CREATE TABLE DTBL_CUS_ORDER (
ORDERID VARCHAR(13) NOT NULL,
CREATEDATE VARCHAR(19),
DIAMONDID VARCHAR(15) NOT NULL,
CLARITY VARCHAR(100),
SHAPE VARCHAR(100),
SIZ VARCHAR(100),
COLOR VARCHAR(100),
COMMENTS VARCHAR(250),
COST DECIMAL(18,2),
PRICE DECIMAL(18,2),
ORDSTATUS VARCHAR(1),
SUPPLIER VARCHAR(100),
SUPPHONE VARCHAR(100),
SUPLOC VARCHAR(100),
CUSNAME VARCHAR(100),
EMAIL VARCHAR(100),
PHONE VARCHAR(20),
SALESNAME VARCHAR(100),
BRANCH VARCHAR(3),
PROPOSED VARCHAR(250),
QUANUM VARCHAR(100),
DATENEED DATE,
STATUS VARCHAR(1),
NOTE VARCHAR(400),
PRIMARY KEY(ORDERID,DIAMONDID));

DROP TABLE DTBL_EXCHANGE_RATE;
CREATE TABLE DTBL_EXCHANGE_RATE (
RATE DECIMAL(18,2) NOT NULL,
UPDDATE VARCHAR(19),
PRIMARY KEY(UPDDATE));

DROP TABLE DTBL_DIAMOND;
CREATE TABLE DTBL_DIAMOND(
    Id                        int           null,
    DiamondId                 int           null,
    BatchId                   varchar(250)  not null,
    Shape                     varchar(250)  null,
    Size                      decimal(18)   not null,
    Color                     varchar(250)  not null,
    Supplier                  varchar(250)  not null,
    LotLocation               varchar(250)  null,
    FancyColorDominantColor   varchar(250)  null,
    FancyColorSecondaryColor  varchar(250)  null,
    FancyColorOvertone        varchar(250)  null,
    FancyColorIntensity       varchar(250)  null,
    Clarity                   varchar(250)  null,
    Cut                       varchar(250)  null,
    Symmetry                  varchar(250)  null,
    Treatment                 varchar(250)  null,
    Polish                    varchar(250)  null,
    DepthPercent              decimal(17)   null,
    TablePercent              decimal(17)   null,
    MeasLength                decimal(17)   null,
    MeasWidth                 decimal(17)   null,
    MeasDepth                 decimal(17)   null,
    GirdleMin                 varchar(10)   null,
    GirdleMax                 varchar(10)   null,
    GirdleCondition           varchar(250)  null,
    CuletSize                 varchar(250)  null,
    CuletCondition            varchar(250)  null,
    FluorColor                varchar(250)  null,
    FluorIntensity            varchar(250)  null,
    HasCertFile               bit           null,
    Lab                       varchar(10)   null,
    PricePerCt                decimal(17)   null,
    RapaportDiscount          decimal(17)   null,
    TotalSalesPrice           decimal(17)   null,
    CurrencyCode              varchar(250)  null,
    CurrencySymbol            varchar(5)    null,
    TotalSalesPriceInCurrency decimal(17)   not null,
    CertNum                   varchar(250)  null,
    StockNum                  varchar(250)  null,
    HasImageFile              bit           null,
    ImageFileUrl              varchar(250)  null,
    HasSarineloupe            bit           null,
    EyeClean                  varchar(250)  null,
    ReportShape               varchar(250)  null,
    ReportComments            varchar(250)  null,
    KeyToSymbols              varchar(250)  null,
    Inclusions                varchar(250)  null,
    MemberComment             varchar(250)  null,
    Brand                     varchar(100)  null,
    Shade                     varchar(100)  null,
    Crown                     varchar(100)  null,
    Pavilion                  varchar(1000) null,
    Inscription               varchar(250)  null,
    StarLength                varchar(250)  null,
    Ratio                     varchar(100)  null,
    Source                    varchar(250)  null,
    Created                   datetime      null,
    Updated                   datetime      null,
    Sold                      datetime      null,
    Rate                      varchar(100)  null,
    SupplierComments          varchar(1000) null,
    Availability              varchar(250)  null,
    HasVideo                  bit           null,
    VideoUrl                  varchar(250)  null
);

DROP TABLE DTBL_SIZE_MAX_MIN;
CREATE TABLE DTBL_SIZE_MAX_MIN (
MAXC DECIMAL(18,2) NOT NULL,
MINC DECIMAL(18,2) NOT NULL,
UPDDATE VARCHAR(19),
PRIMARY KEY(UPDDATE));


DROP table DTBL_ORDER_ID;
create table DTBL_ORDER_ID(
ID int NOT NULL,
BRANCH VARCHAR(3) NOT NULL,
CREATEDATE date NOT NULL,
primary key(BRANCH, CREATEDATE));


DROP table DTBL_LOG_LOGIN;
create table DTBL_LOG_LOGIN(
EMAIL VARCHAR(100) NOT NULL,
BRANCH VARCHAR(3) NOT NULL,
CREATEDATE VARCHAR(19) NOT NULL,
primary key(CREATEDATE));

