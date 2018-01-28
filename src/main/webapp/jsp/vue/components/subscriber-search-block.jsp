<script type="text/x-template" id="subscriber-search-block-template">
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
            <div v-if="subscribers.length>0" class="row center-align">
                <div class="col s2">
                    <p><b>{{first_name_text}}</b></p>
                </div>
                <div class="col s2">
                    <p><b>{{last_name_text}}</b></p>
                </div>
                <div class="col s2">
                    <p><b>{{login_text}}</b></p>
                </div>
                <div class="col s1">
                    <p><b>{{balance_text}}</b></p>
                </div>
                <div class='col s2'>
                    <p><b>{{birthday_text}}</b></p>
                </div>
            </div>

            <template v-if="noResults">
                <h5>No results</h5>
                <br>
            </template>

            <div v-for="subscriber in subscribers">
                <div class="tariff-row-card teal lighten-5">
                    <div class="row zero-margin center-align">
                        <div class="row center-align">
                            <div class="col s2">{{subscriber.firstName}}</div>
                            <div class="col s2">{{subscriber.lastName}}</div>
                            <div class="col s2">{{subscriber.user.login}}</div>
                            <div class="col s1">
                                {{subscriber.account.balance}}
                                {{subscriber.account.currencyShortname}}
                            </div>
                            <div class="col s2">{{subscriber.birthDate}}</div>
                            <div class="col s2">
                                <ban-button
                                    :banned_state="subscriber.user.banned"
                                    :user_id="subscriber.user.id"
                                ></ban-button>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>
    </div>
</script>

<script>
    Vue.component('subscriber-search-block', {
        data() {
            return {
                subscribers: [],
                searchQ: "",
                noResults: false,

                first_name_text: strings['first_name'],
                last_name_text: strings['last_name'],
                login_text: strings['login'],
                balance_text: strings['balance'],
                birthday_text: strings['birth_date'],
            }
        },
        methods: {
            search() {
                this.subscribers = [];
                axios.get("/api/subscriber/search?query=" + encodeURIComponent(this.searchQ))
                     .then((response) => {
                         console.log(response);
                         this.subscribers = response.data;
                         if (this.subscribers.length === 0) {
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
        template: "#subscriber-search-block-template",
    })
</script>