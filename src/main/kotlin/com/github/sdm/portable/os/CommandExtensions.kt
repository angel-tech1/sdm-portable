package com.github.sdm.portable.os

import java.io.File
import java.util.concurrent.TimeUnit

fun String.runCommand(
    workingDir: File = File("."),
    timeoutAmount: Long = 60,
    timeoutUnit: TimeUnit = TimeUnit.SECONDS,
    afterCommand: () -> Unit = {}
): String? {
    println("Command: $this")

    return """
         DATASOURCE                                               STATUS                                              ADDRESS             TYPE                TAGS
     atlas-beauto-india-cassandra                             not connected                                       127.0.0.1:19051     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-mariadb                               not connected                                       127.0.0.1:13362     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis                                 not connected                                       127.0.0.1:16411     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-acceptance                      not connected                                       127.0.0.1:16406     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-approval                        not connected                                       127.0.0.1:16405     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-assignment                      not connected                                       127.0.0.1:16407     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-auth                            not connected                                       127.0.0.1:16401     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-comms                           not connected                                       127.0.0.1:16402     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-dsrouter                        not connected                                       127.0.0.1:16404     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-dss                             not connected                                       127.0.0.1:16400     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-india-redis-loans                           not connected                                       127.0.0.1:16403     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-ke-cassandra                                not connected                                       127.0.0.1:10013     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-ke-cassandra-users                          not connected                                       127.0.0.1:10030     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-ke-mariadb                                  not connected                                       127.0.0.1:10019     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-mariadb                                  not connected                                       127.0.0.1:10012     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-mysql                                    not connected                                       127.0.0.1:10048     mysql               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-redis                                    not connected                                       127.0.0.1:16421     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-redis-auth                               not connected                                       127.0.0.1:16423     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-redis-bms                                not connected                                       127.0.0.1:16429     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-redis-dss                                not connected                                       127.0.0.1:16422     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-mx-redis-legacy-acceptance                  not connected                                       127.0.0.1:16413     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-ph-mariadb                                  not connected                                       127.0.0.1:10161     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-ph-redis-account                            not connected                                       127.0.0.1:16416     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-beauto-ph-redis-legacy-acceptance                  not connected                                       127.0.0.1:16414     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-india-mariadb                                  not connected                                       127.0.0.1:10165     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-india-mariadb-restored                         not connected                                       127.0.0.1:10152     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-india-redis                                    not connected                                       127.0.0.1:16409     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-india-redis-auth                               not connected                                       127.0.0.1:16432     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-india-redis-loans                              not connected                                       127.0.0.1:16408     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-ke-cassandra                                   not connected                                       127.0.0.1:10014     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-ke-mariadb                                     not connected                                       127.0.0.1:10020     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-ke-redis-auth-proxy                            not connected                                       127.0.0.1:10187     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-mx-approval-redis                              not connected                                       127.0.0.1:16424     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-mx-mariadb                                     not connected                                       127.0.0.1:1642      maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-mx-mysql                                       not connected                                       127.0.0.1:10198     mysql               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-mx-redis                                       not connected                                       127.0.0.1:10186     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-mx-redis-bms                                   not connected                                       127.0.0.1:16428     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-mx-redis-loans                                 not connected                                       127.0.0.1:16425     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-pe-cassandra                                   not connected                                       127.0.0.1:10136     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-ph-mariadb                                     not connected                                       127.0.0.1:10133     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-dev-ph-redis-account                               not connected                                       127.0.0.1:16417     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-mariadb                                   not connected                                       127.0.0.1:13351     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis                                     not connected                                       127.0.0.1:16388     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-acceptance                          not connected                                       127.0.0.1:16397     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-approval                            not connected                                       127.0.0.1:16392     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-assignment                          not connected                                       127.0.0.1:16398     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-auth                                not connected                                       127.0.0.1:16393     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-comms                               not connected                                       127.0.0.1:16394     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-dsrouter                            not connected                                       127.0.0.1:16399     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-dss                                 not connected                                       127.0.0.1:16395     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-india-redis-loans                               not connected                                       127.0.0.1:16396     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-ke-cassandra                                    not connected                                       127.0.0.1:10025     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-ke-mariadb                                      not connected                                       127.0.0.1:10021     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-mx-mariadb                                      not connected                                       127.0.0.1:10167     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-mx-redis                                        not connected                                       127.0.0.1:10189     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-pe-cassandra                                    not connected                                       127.0.0.1:10162     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-ph-mariadb                                      not connected                                       127.0.0.1:13507     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-ph-redis-acceptance                             not connected                                       127.0.0.1:16427     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-qa-ph-redis-account                                not connected                                       127.0.0.1:16418     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-cassandra                              not connected                                       127.0.0.1:19045     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-redis-acceptance                       not connected                                       127.0.0.1:16389     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-redis-approval                         not connected                                       127.0.0.1:16384     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-redis-assignment                       not connected                                       127.0.0.1:16390     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-redis-auth                             not connected                                       127.0.0.1:16385     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-redis-dsrouter                         not connected                                       127.0.0.1:16391     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-india-redis-dss                              not connected                                       127.0.0.1:16387     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-ke-cassandra                                 not connected                                       127.0.0.1:10036     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-ke-mariadb                                   not connected                                       127.0.0.1:10022     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-mx-cassandra                                 not connected                                       127.0.0.1:1026      cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-mx-mariadb                                   not connected                                       127.0.0.1:13345     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-mx-redis                                     not connected                                       127.0.0.1:10188     redis               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-pe-cassandra                                 not connected                                       127.0.0.1:10169     cassandra           atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-ph-mariadb                                   not connected                                       127.0.0.1:13508     maria               atlas=,auto-approve=,devops=,non-prod=
     atlas-stage-ph-redis-account                             not connected                                       127.0.0.1:16419     redis               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-india-cassandra                      not connected                                       127.0.0.1:19064     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-india-mariadb                        not connected                                       127.0.0.1:13466     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-ke-cassandra                         not connected                                       127.0.0.1:10034     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-ke-mariadb                           not connected                                       127.0.0.1:10015     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-ke-mysql                             not connected                                       127.0.0.1:10039     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-mx-cassandra                         not connected                                       127.0.0.1:19053     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-mx-mariadb                           not connected                                       127.0.0.1:13488     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-mx-mysql                             not connected                                       127.0.0.1:13414     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-ph-cassandra                         not connected                                       127.0.0.1:1028      cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-beauto-ph-mariadb                           not connected                                       127.0.0.1:13379     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-india-cassandra                         not connected                                       127.0.0.1:19052     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-india-mariadb                           not connected                                       127.0.0.1:13494     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-ke-cassandra                            not connected                                       127.0.0.1:10035     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-ke-mariadb                              not connected                                       127.0.0.1:10016     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-ke-mysql                                not connected                                       127.0.0.1:10041     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-mx-cassandra                            not connected                                       127.0.0.1:19062     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-mx-mariadb                              not connected                                       127.0.0.1:13382     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-mx-mysql                                not connected                                       127.0.0.1:13307     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-pe-mariadb                              not connected                                       127.0.0.1:10120     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-ph-cassandra                            not connected                                       127.0.0.1:19050     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-dev-ph-mariadb                              not connected                                       127.0.0.1:13380     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-india-cassandra                          not connected                                       127.0.0.1:19044     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-india-mariadb                            not connected                                       127.0.0.1:13403     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-ke-cassandra                             not connected                                       127.0.0.1:10033     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-ke-mariadb                               not connected                                       127.0.0.1:10017     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-ke-mysql                                 not connected                                       127.0.0.1:10042     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-mx-cassandra                             not connected                                       127.0.0.1:19056     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-mx-mariadb                               not connected                                       127.0.0.1:13392     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-mx-mysql                                 not connected                                       127.0.0.1:13398     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-pe-mariadb                               not connected                                       127.0.0.1:10163     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-ph-cassandra                             not connected                                       127.0.0.1:19067     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-qa-ph-mariadb                               connected                                           127.0.0.1:13320     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-india-cassandra                       not connected                                       127.0.0.1:19054     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-india-mariadb                         not connected                                       127.0.0.1:13311     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-ke-cassandra                          not connected                                       127.0.0.1:10037     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-ke-mariadb                            not connected                                       127.0.0.1:10018     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-ke-mysql                              not connected                                       127.0.0.1:10040     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-mx-cassandra                          not connected                                       127.0.0.1:1025      cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-mx-mariadb                            not connected                                       127.0.0.1:13399     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-mx-mysql                              not connected                                       127.0.0.1:10141     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-mx-payments-mysqldb                   not connected                                       127.0.0.1:13354     mysql               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-pe-mariadb                            not connected                                       127.0.0.1:10098     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-ph-cassandra                          not connected                                       127.0.0.1:19068     cassandra           atlas=,auto-approve=,devops=,non-prod=
     devops-atlas-stage-ph-mariadb                            not connected                                       127.0.0.1:13433     maria               atlas=,auto-approve=,devops=,non-prod=
     devops-iac-stage-ke-mx-mariadb                           not connected                                       127.0.0.1:13489     mysql               auto-approve=,devops=,legacy=,non-prod=
     devops-iac-stage-ph-mariadb                              not connected                                       127.0.0.1:13492     mysql               auto-approve=,devops=,legacy=,non-prod=
     devops-legacy-dev-mx-internal-testing                    not connected                                       127.0.0.1:13542     maria               auto-approve=,devops=,legacy=,non-prod=
     devops-legacy-dev-mx-internal-testing-restored           not connected                                       127.0.0.1:10156     maria               auto-approve=,devops=,legacy=,non-prod=
     devops-legacy-dev-pitaka                                 not connected                                       127.0.0.1:13329     mysql               auto-approve=,devops=,legacy=,non-prod=
     devops-legacy-dev-qa-ke-internal-testing                 not connected                                       127.0.0.1:3406      mysql               auto-approve=,devops=,legacy=,non-prod=
     devops-legacy-dev-qa-ph-internal-testing                 not connected                                       127.0.0.1:13527     mysql               auto-approve=,devops=,legacy=,non-prod=
     devops-legacy-stage-ph-internal-testing                  not connected                                       127.0.0.1:13499     mysql               auto-approve=,devops=,legacy=,non-prod=
     iac-stage-ke-mx-mariadb                                  not connected                                       127.0.0.1:13520     mysql               auto-approve=,devops=,legacy=,non-prod=
     iac-stage-ph-mariadb                                     not connected                                       127.0.0.1:13523     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-Stage-KE-MX-Cassandra                             not connected                                       127.0.0.1:19071     cassandra           auto-approve=,devops=,legacy=,non-prod=
     legacy-Stage-PH-Cassandra                                not connected                                       127.0.0.1:19072     cassandra           auto-approve=,devops=,legacy=,non-prod=
     legacy-dev-KE-MX-PH-Cassandra                            not connected                                       127.0.0.1:19066     cassandra           auto-approve=,devops=,legacy=,non-prod=
     legacy-dev-mx-main                                       not connected                                       127.0.0.1:13309     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-dev-qa-ke-internal-testing                        not connected                                       127.0.0.1:13502     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-dev-qa-ke-internal-testing-restored               not connected                                       127.0.0.1:10157     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-dev-qa-ph-internal-testing                        not connected                                       127.0.0.1:3706      mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-dev-qa-ph-internal-testing-restored               not connected                                       127.0.0.1:10158     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-non-prod-mariadb                                  not connected                                       127.0.0.1:13321     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-qa-ke-mx-ph-cassandra                             not connected                                       127.0.0.1:19070     cassandra           auto-approve=,devops=,legacy=,non-prod=
     legacy-stage-ph-internal-testing                         not connected                                       127.0.0.1:13498     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-stage-ph-internal-testing-restored-instance       not connected                                       127.0.0.1:10153     mysql               auto-approve=,devops=,legacy=,non-prod=
     legacy-stage-ph-internal-testing-restored-instance-2     not connected                                       127.0.0.1:10154     mysql               auto-approve=,devops=,legacy=,non-prod=

     SERVER                                                   STATUS                                              LOCK STATUS         ADDRESS             TYPE               TAGS
     legacy-dev-mx-payments-haproxy-ipsec                     connected (auto)                                    n/a                 127.0.0.1:10004     ssh                auto-approve=,legacy=,non-prod=
     legacy-dev-mx-payments-srv1                              connected (auto)                                    n/a                 127.0.0.1:10003     ssh                auto-approve=,legacy=,non-prod=
     legacy-qa-mx-payments-haproxy-ipsec                      connected (auto)                                    n/a                 127.0.0.1:10006     ssh                auto-approve=,legacy=,non-prod=
     legacy-qa-mx-payments-srv1                               connected (auto)                                    n/a                 127.0.0.1:10005     ssh                auto-approve=,legacy=,non-prod=
     legacy-stage-mx-payments-haproxy-ipsec                   connected (auto)                                    n/a                 127.0.0.1:10075     ssh                auto-approve=,legacy=,non-prod=
     legacy-stage-mx-payments-srv1                            connected (auto)                                    n/a                 127.0.0.1:10007     ssh                auto-approve=,legacy=,non-prod=
     legacy-stage-mx-payments-srv2                            connected (auto)                                    n/a                 127.0.0.1:10008     ssh                auto-approve=,legacy=,non-prod=
     new-non-prod-ph-payments-node-test-srv                   connected (auto)                                    n/a                 127.0.0.1:10164     sshCustomerKey     auto-approve=,legacy=,non-prod=
     new-non-prod-ph-payments-node-test-srv2                  connected (auto)                                    n/a                 127.0.0.1:10171     sshCustomerKey     auto-approve=,legacy=,non-prod=
     non-prod-ke-payments-srv                                 connected (auto)                                    n/a                 127.0.0.1:10001     sshCustomerKey     auto-approve=,legacy=,non-prod=
     non-prod-ph-payments-srv                                 connected (auto)                                    n/a                 127.0.0.1:33514     sshCustomerKey     auto-approve=,legacy=,non-prod=

     WEBSITE                                                  URL                                                 TAGS
     legacy-dev-ke-api                                        https://dev-ke-api.tala.sdm.network                 auto-approve=,legacy=,non-prod=
     legacy-dev-ke-payments                                   https://ke-dev-payments.tala.sdm.network            auto-approve=,legacy=,non-prod=
     legacy-dev-ke-profiling                                  https://dev-ke-profiling.tala.sdm.network           auto-approve=,legacy=,non-prod=
     legacy-dev-mx-API                                        https://dev-mx-api.tala.sdm.network                 auto-approve=,legacy=,non-prod=
     legacy-dev-mx-Profiling                                  https://dev-mx-profiling.tala.sdm.network           auto-approve=,legacy=,non-prod=
     legacy-dev-mx-payments                                   https://mx-dev-payments.tala.sdm.network            auto-approve=,legacy=,non-prod=
     legacy-dev-ph-API                                        https://dev-ph-api.tala.sdm.network                 auto-approve=,legacy=,non-prod=
     legacy-dev-ph-Profiling                                  https://dev-ph-profiling.tala.sdm.network           auto-approve=,legacy=,non-prod=
     legacy-dev-ph-payments                                   https://ph-dev-payments.tala.sdm.network            auto-approve=,legacy=,non-prod=
     legacy-qa-ke-Profiling                                   https://qa-ke-profiling.tala.sdm.network            auto-approve=,legacy=,non-prod=
     legacy-qa-ke-api                                         https://qa-ke-api.tala.sdm.network                  auto-approve=,legacy=,non-prod=
     legacy-qa-ke-payments                                    https://ke-qa-payments.tala.sdm.network             auto-approve=,legacy=,non-prod=
     legacy-qa-mx-API                                         https://qa-mx-api.tala.sdm.network                  auto-approve=,legacy=,non-prod=
     legacy-qa-mx-Profiling                                   https://qa-mx-profiling.tala.sdm.network            auto-approve=,legacy=,non-prod=
     legacy-qa-mx-payments                                    https://mx-qa-payments.tala.sdm.network             auto-approve=,legacy=,non-prod=
     legacy-qa-ph-API                                         https://qa-ph-api.tala.sdm.network                  auto-approve=,legacy=,non-prod=
     legacy-qa-ph-Profiling                                   https://qa-ph-profiling.tala.sdm.network            auto-approve=,legacy=,non-prod=
     legacy-qa-ph-payments                                    https://ph-qa-payments.tala.sdm.network             auto-approve=,legacy=,non-prod=
     legacy-stage-ke-Profiling                                https://stage-ke-profiling.tala.sdm.network         auto-approve=,legacy=,non-prod=
     legacy-stage-ke-payments                                 https://ke-stage-payments.tala.sdm.network          auto-approve=,legacy=,non-prod=
     legacy-stage-mx-Profiling                                https://stage-mx-profiling.tala.sdm.network         auto-approve=,legacy=,non-prod=
     legacy-stage-mx-payments                                 https://mx-stage-payments.tala.sdm.network          auto-approve=,legacy=,non-prod=
     legacy-stage-ph-Profiling                                https://stage-ph-profiling.tala.sdm.network         auto-approve=,legacy=,non-prod=
     legacy-stage-ph-payments                                 https://ph-stage-payments.tala.sdm.network          auto-approve=,legacy=,non-prod=
     legacy-v1-qa-ke-payments                                 https://ke-qa-payments-v1.tala.sdm.network          auto-approve=,legacy=,non-prod=
     legacy-v1-qa-ph-payments                                 https://ph-qa-payments-v1.tala.sdm.network          auto-approve=,legacy=,non-prod=
     legacy-v1-stage-ke-payments                              https://ke-staging-payments-v1.tala.sdm.network     auto-approve=,legacy=,non-prod=
     legacy-v1-stage-ph-payments                              https://ph-stage-payments-v1.tala.sdm.network       auto-approve=,legacy=,non-prod=

""".trimIndent() }/*runCatching {
    ProcessBuilder("\\s".toRegex().split(this))
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.PIPE)
        .redirectError(ProcessBuilder.Redirect.PIPE)
        .start().also { it.waitFor(timeoutAmount, timeoutUnit) }.also { afterCommand() }
        .inputStream.bufferedReader().readText()
}.onFailure { it.printStackTrace() }.getOrNull()*/
