<script type="text/x-template" id="tariff-row-subscriber-template">
    <div class="tariff-row-card teal lighten-5">
        <div class="row zero-margin center-align">
            <template v-if="d_tariff.serviceTitle">
                <div class="col s2">
                    {{d_tariff.serviceTitle}}
                </div>
            </template>

            <div @mouseover="showTruncated"
                 @mouseleave="hideTruncated"
                 v-bind:class="{truncate:isTruncated}"
                 class="col s2">

                {{d_tariff.title}}
            </div>
            <div @mouseover="showTruncated"
                 @mouseleave="hideTruncated"
                 v-bind:class="{truncate:isTruncated}"
                 class="col s2">

                {{d_tariff.description}}
            </div>
            <div class="col s1">{{d_tariff.numberOfDays}}</div>
            <div class="col s1">{{d_tariff.cost}} {{d_tariff.currencyShortname}}</div>
            <div class="col s2">{{dueDate}}</div>

            <div class="col s2">
                <a v-if="!isSubscribed"
                   :disabled="d_user.banned"
                   @click="subscribe"
                   class="waves-effect waves-light btn btn-small noselect">
                    {{buttonText}}
                </a>
                <a v-if="isSubscribed"
                   @click="unsubscribe"
                   class="waves-effect waves-light btn btn-small noselect">
                    {{buttonText}}
                </a>
            </div>
        </div>
    </div>
</script>

<script>
    Vue.component('tariff-row-subscriber', {
        props: ['user', 'tariff', 'subscriptions'],
        data: function () {
            return {
                isSubscribed: false,
                isProlong: false,
                dueDate: "-------------",
                isBanned: false,
                isTruncated: true,
                d_subscriptions: JSON.parse(this.subscriptions),
                d_tariff: "",
                d_user: ""


            }
        },
        template: "#tariff-row-subscriber-template",
        methods: {
            subscribe() {
                console.log("Subscribe: userId: " + this.d_user.id
                    + " tariffId: " + this.d_tariff.id);
                axios.post("/api/user/subscribe", {
                    'userId': this.d_user.id,
                    'tariffId': this.d_tariff.id
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast(strings['subscription.success'], 1500);
                        this.isSubscribed = true;
                        this.dueDate = this.getDueDate();
                    } else {
                        Materialize.toast(strings['subscription.fail'], 1500)
                    }

                }).catch((error) => {
                    console.log(error);
                })

            },

            unsubscribe() {

                axios.post("/api/user/unsubscribe", {
                    'userId': this.d_user.id,
                    'tariffId': this.d_tariff.id
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast(strings['unsubscription.success'], 1500);
                        this.isSubscribed = false;
                    } else {
                        Materialize.toast(strings['unsubscription.fail'], 1500);
                    }

                }).catch((error) => {
                    console.log(error);
                })
            },

            getDueDate() {
                return moment().add(this.number_of_days, 'days')
                               .format("YYYY-MM-DD");
            },

            showTruncated() {
                this.isTruncated = false;
            },
            hideTruncated() {
                this.isTruncated = true;
            },

            isString(value) {
                return typeof value === 'string' || value instanceof String;
            }

        },

        mounted: function () {
            const tariff = this.tariff;
            this.d_tariff = this.isString(tariff) ? JSON.parse(tariff) : tariff;
            this.d_user = this.isString(this.user) ? JSON.parse(this.user) : this.user;
            console.log("subscriber tariff row tariff: ", this.d_tariff);
            console.log("Subscriber user: ", this.d_user);

            try {
                const subscriptions = JSON.parse(this.subscriptions);

                subscriptions.forEach((elem) => {
                    if (elem.tariffId == this.d_tariff.id) {
                        this.isSubscribed = elem.prolong == true;
                        if (Date.parse(elem.end) > Date.now()) {
                            this.dueDate = elem.end;
                        }
                    }
                });
            } catch (error) {

            }

        },

        computed: {
            buttonText() {
                return this.isSubscribed
                    ? strings['unsubscribe']
                    : strings['subscribe'];
            }
        }
    })
</script>