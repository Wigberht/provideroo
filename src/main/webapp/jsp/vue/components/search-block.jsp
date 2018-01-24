<script type="text/x-template" id="search-block-template">
    <div>
        <div class="search-block">
            <div class="row">
                <div class="col s10">
                    <input type="text" v-model="searchQ" @keyup.enter="search">
                </div>
                <div @click="search" class="btn col s2">
                    search
                </div>
            </div>
        </div>
        <div class="search-response-block">
            <template v-if="noResults">
                <h5>No results</h5>
            </template>
            <template v-if="isAdmin">
                <tariff-row-admin
                    v-for="tariff in tariffs"

                    :id="tariff.id"
                    :title="tariff.title"
                    :description="tariff.description"
                    :number_of_days="tariff.numberOfDays"
                    :cost="tariff.cost"
                    :currency="tariff.currencyShortname"
                    :service="tariff.serviceTitle"

                    :subscribers="tariff.subscriberAmount"
                ></tariff-row-admin>
            </template>

            <template v-if="!isAdmin">
                <tariff-row-subscriber
                    v-for="tariff in tariffs"

                    :id="tariff.id"
                    :title="tariff.title"
                    :description="tariff.description"
                    :number_of_days="tariff.numberOfDays"
                    :cost="tariff.cost"
                    :currency="tariff.currencyShortname"
                    :service="tariff.serviceTitle"

                    :banned="is_banned"
                    :subscriptions="subscriptions"
                    :user_id="user_id"
                ></tariff-row-subscriber>
            </template>
        </div>
    </div>
</script>

<script>
    Vue.component('search-block', {
        props: [
            'is_admin', 'subscriptions', 'user_id', 'is_banned',
        ],
        data() {
            return {
                tariffs: [],
                isAdmin: this.is_admin == "true",
                searchQ: "",
                subscriptions: [],
                noResults: false
            }
        },
        methods: {
            search() {
                axios.get("/rest/tariff/search?query=" + encodeURIComponent(this.searchQ))
                     .then((response) => {
                         console.log(response);
                         this.tariffs = response.data;
                         if (this.tariffs.length === 0) {
                             this.showNoResults();
                         }
                     }).catch((error) => {
                        Materialize.toast("Toggle fail", 1500);
                    }
                )
            },

            showNoResults() {
                this.noResults = true;
                setTimeout(() => {
                    this.noResults = false;
                }, 2000);
            }

        },
        mounted() {
            console.log("search-block mounted");
        },
        template: "#search-block-template",
    })
</script>