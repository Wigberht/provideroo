<script type="text/x-template" id="tariff-search-block-template">
    <div>
        <div class="search-block">
            <div class="row">
                <div class="col s10">
                    <input type="text" v-model="searchQ" @keyup.enter="search">
                </div>
                <div @click="search" class="btn col s2">
                    {{searchText}}
                </div>
            </div>
        </div>
        <div class="search-response-block">
            <div v-if="tariffs.length>0" class="row center-align">
                <div class="col s2">
                    <p><b>{{service_title_text}}</b></p>
                </div>
                <div class="col s2">
                    <p><b>{{title_text}}</b></p>
                </div>
                <div class="col s2">
                    <p><b>{{description_text}}</b></p>
                </div>
                <div class="col s1">
                    <p><b>{{number_of_days_text}}</b></p>
                </div>
                <div class='col s1'>
                    <p><b>{{cost_text}}</b></p>
                </div>
                <template v-if="!d_user.admin">
                    <div class="col s2">
                        <p><b>{{due_date_text}}</b></p>
                    </div>
                </template>
                <template v-if="d_user.admin">
                    <div class="col s1">
                        <p><b>{{subscribers_amount_text}}</b></p>
                    </div>
                </template>
            </div>

            <template v-if="noResults">
                <h5>{{noResultsText}}</h5>
                <br>
            </template>

            <tariff-row
                v-for="tariff in tariffs"

                :tariff='tariff'
                :user='user'
                :subscriptions='subscriptions'
            ></tariff-row>
        </div>
    </div>
</script>

<script>
    Vue.component('tariff-search-block', {
        props: [
            'user', 'subscriptions',
        ],
        data() {
            return {
                d_user: JSON.parse(this.user),
                tariffs: [],
                searchQ: "",
                noResults: false,
                noResultsText: strings['no_results'],

                searchText: strings['search'],

                service_title_text: strings['service'],
                title_text: strings['title'],
                description_text: strings['description'],
                number_of_days_text: strings['number_of_days'],
                cost_text: strings['cost'],
                due_date_text: strings['due_date'],
                subscribers_amount_text: strings['subscribers_amount'],
            }
        },
        methods: {
            search() {
                this.tariffs = [];
                axios.get("/api/tariff/search?query=" + encodeURIComponent(this.searchQ))
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
        mounted() { },
        template: "#tariff-search-block-template",
    })
</script>