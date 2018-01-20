<script type="text/x-template" id="tariff-row-subscriber-template">

    <div class="row zero-margin center-align">
        <div class="col s2">{{title}}</div>
        <div class="col s3">{{description}}</div>
        <div class="col s1">{{number_of_days}}</div>
        <div class="col s1">{{cost}}</div>
        <div class="col s1">{{currency}}</div>
        <div class="col s2">{{dueDate}}</div>

        <div class="col s2">
            <a v-if="!isSubscribed"
               :disabled="isBanned"
               @click="subscribe"
               class="waves-effect waves-light btn btn-small noselect">
                {{buttonText}}
            </a>
            <a v-if="isSubscribed"
               @click="unsubscribe"
               class="waves-effect waves-light btn btn-small noselect">
                {{buttonText}}
            </a>
            <%--<p v-if=""--%>
        </div>

    </div>

</script>

<script>
    Vue.component('tariff-row-subscriber', {
        props: ['banned',
            'id', 'title', 'description',
            'number_of_days', 'cost', 'currency',
            'subscribe_text', 'unsubscribe_text', 'subscribed',
            'subscriptions', 'user_id',
            'subscription_fail_text', 'subscription_success_text',
            'subscription_prolong_fail_text', 'subscription_prolong_success_text',
        ],
        data: function () {
            return {
                isSubscribed: false,
                isProlong: false,
                dueDate: "-------------",
                isBanned: this.banned == "true"
            }
        },
        template: "#tariff-row-subscriber-template",
        methods: {
            subscribe() {
                axios.post("/rest/user/subscribe", {
                    'userId': this.user_id,
                    'tariffId': this.id
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast(this.subscription_success_text, 1500);
                        this.isSubscribed = true;
                        this.dueDate = this.getDueDate();
                    } else {
                        Materialize.toast(this.subscription_fail_text, 1500)
                    }

                }).catch((error) => {
                    console.log(error);
                })

            },

            unsubscribe() {
                axios.post("/rest/user/unsubscribe", {
                    'userId': this.user_id,
                    'tariffId': this.id
                }).then((response) => {
                    console.log(response);
                    if (response.data.success) {
                        Materialize.toast(this.subscription_success_text, 1500);
                        this.isSubscribed = false;
                    } else {
                        Materialize.toast(this.subscription_fail_text, 1500)
                    }

                }).catch((error) => {
                    console.log(error);
                })
            },

            getDueDate() {
                return moment().add(this.number_of_days, 'days')
                               .format("YYYY-MM-DD");
            }

        },

        beforeMount() {
            const subscriptions = JSON.parse(this.subscriptions);

            subscriptions.forEach((elem) => {
                if (elem.tariffId == this.id) {
                    this.isSubscribed = elem.prolong == true;
                    if (Date.parse(elem.end) > Date.now()) {
                        this.dueDate = elem.end;
                    }
                    console.log("subscribed " + this.isSubscribed);
                }
            });
        },
        mounted: function () {

            console.log("Banned: " + Boolean(this.banned));
            console.log("Is banned: ", Boolean(this.banned));
        },

        computed: {
            buttonText() {
                return this.isSubscribed ? this.unsubscribe_text : this.subscribe_text;
            }
        }
    })
</script>